package com.example.prycursosequipo1.Vista

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.prycursosequipo1.Presentador.clsRegistroPresentador
import com.example.prycursosequipo1.R
import java.util.Calendar
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo

class activity_registro_cursos : AppCompatActivity() {
    private lateinit var presenter: clsRegistroPresentador

    private lateinit var txtNombreCursoEncabezado: TextView
    private lateinit var txtNombreCurso: TextView
    private lateinit var txtMatricula: EditText
    private lateinit var txtFechaRegistro: EditText
    private lateinit var txtCorreo: EditText
    private lateinit var btnConfirmar: Button
    private lateinit var btnCancelar: Button
    private var idCurso: Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_cursos)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar los componentes
        txtNombreCursoEncabezado = findViewById(R.id.txtSubTitulo)
        txtNombreCurso = findViewById(R.id.txtNombreCurso)
        txtMatricula = findViewById(R.id.txtMatricula)
        txtFechaRegistro = findViewById(R.id.txtFechaRegistro)
        txtCorreo = findViewById(R.id.txtGmail)
        btnConfirmar = findViewById(R.id.btnConfirmar)
        btnCancelar = findViewById(R.id.btnCancelar)



        // Recibir los datos del curso seleccionado
        idCurso = intent.getIntExtra("idCurso", -1)
        val nombreCurso = intent.getStringExtra("nombreCurso") ?: ""
        val imagenCurso = intent.getStringExtra("imagenCurso")

        // Mostrar el nombre del curso
        txtNombreCurso.text = nombreCurso
        val imgCurso = findViewById<ImageView>(R.id.imgCurso)

        val baseUrl = "http://192.168.5.30/cursos_api/"
        val urlImagenCompleta = baseUrl + imagenCurso

        Glide.with(this)
            .load(urlImagenCompleta)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.stat_notify_error)
            .into(imgCurso)

        // Inicializar el presentador
        presenter = clsRegistroPresentador(this)

        // Eventos de los botones
        txtFechaRegistro.setOnClickListener(this::seleccionarFecha)
        btnConfirmar.setOnClickListener(this::ejecutarConfirmar)
        txtMatricula.setOnEditorActionListener(this::buscarMatricula)

        btnCancelar.setOnClickListener { finish() }
    }

    fun ejecutarConfirmar(v: View){
        val matricula = txtMatricula.text.toString().trim()
        val fechaRegistro = txtFechaRegistro.text.toString().trim()
        val correo = txtCorreo.text.toString().trim()

        presenter.realizarRegistro(matricula, fechaRegistro, correo, idCurso)
    }

    fun seleccionarFecha(v: View){
        val calendario = Calendar.getInstance()
        val selectorFecha = DatePickerDialog(
            this,
            { _, anio, mes, dia ->
                val fecha = String.format("%04d-%02d-%02d", anio, mes + 1, dia)
                txtFechaRegistro.setText(fecha)
            },
            calendario.get(Calendar.YEAR),
            calendario.get(Calendar.MONTH),
            calendario.get(Calendar.DAY_OF_MONTH)
        )

        selectorFecha.show()
    }

    fun buscarMatricula(v: TextView?, i: Int, event: KeyEvent?): Boolean {
        if (i == EditorInfo.IME_ACTION_DONE) {
            val matricula = txtMatricula.text.toString().trim()
            presenter.buscarAlumno(txtMatricula.text.toString().trim())
        }
        return false
    }


    fun mostrarCorreo(correo: String) {
        txtCorreo.setText(correo)
    }

    fun registroExitoso(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        finish()
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}