package com.example.prycursosequipo1.Vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prycursosequipo1.Adaptador.CursosAdaptador
import com.example.prycursosequipo1.EstadoInscripcionesActivity
import com.example.prycursosequipo1.Modelo.dataCarreras
import com.example.prycursosequipo1.Modelo.dataCursos
import com.example.prycursosequipo1.R
import com.example.prycursosequipo1.Presentador.clsCarrerasPresentador

class MainActivity : AppCompatActivity() {
    private lateinit var presentador: clsCarrerasPresentador
    private lateinit var spinner: Spinner
    private lateinit var adaptador: CursosAdaptador
    private lateinit var recyclerCursos: RecyclerView
    private lateinit var btnVerRegistros: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        spinner = findViewById(R.id.spinnerCarreras)
        recyclerCursos = findViewById(R.id.recyclerCursos)

        recyclerCursos.layoutManager = GridLayoutManager(this, 2)
        adaptador = CursosAdaptador( emptyList()){ curso -> }
        recyclerCursos.adapter = adaptador
        presentador = clsCarrerasPresentador(this)
        btnVerRegistros = findViewById(R.id.btnVerRegistros)
        configurarListeners()
        presentador.iniciar()
    }

    private fun configurarListeners(){
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                presentador.seleccionarCarrera(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
            // Botón VER REGISTROS
            btnVerRegistros.setOnClickListener {
                val intent = Intent(this, EstadoInscripcionesActivity::class.java)
                startActivity(intent)
            }
    }

    fun mostrarCarreras(datos: List<dataCarreras>){
        val adapter= ArrayAdapter(this, R.layout.item_spinner, datos)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter=adapter
    }

    fun mostrarCursos(datos: List<dataCursos>){
        adaptador.actualizarLista(datos)
    }

    //Seleccionar curso

    //Aqui va lo relacionado a la vista detalle


    fun mostrarMensajeExito(id: Int, nombre: String){
        Toast.makeText(
            this,
            "ID: "+id.toString()+"- Seleccionaste: "+nombre,
            Toast.LENGTH_SHORT
        ).show()
    }

    fun mostrarError(mensaje: String){
        Toast.makeText(
            this,
            mensaje,
            Toast.LENGTH_LONG
            ).show()
    }
}