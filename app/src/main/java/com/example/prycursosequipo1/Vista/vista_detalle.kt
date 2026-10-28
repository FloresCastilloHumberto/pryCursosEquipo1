package com.example.prycursosequipo1.Vista

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.prycursosequipo1.R

class vista_detalle : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalle_vista)

        // Configurar el botón Back para cerrar la vista
        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

        // Vincular los componentes con los IDs exactos del XML
        val tvTitulo = findViewById<TextView>(R.id.tvTituloDetalle)
        val tvCodigo = findViewById<TextView>(R.id.codigoDetalle)
        val tvDescripcion = findViewById<TextView>(R.id.espacioDescripocion)
        val tvSalon = findViewById<TextView>(R.id.textView7)
        val tvHoras = findViewById<TextView>(R.id.textView9)
        val tvDias = findViewById<TextView>(R.id.textView10)
        val imgCurso = findViewById<ImageView>(R.id.imgCursoDetalle)

        // Recibir los datos enviados por Intent
        val nombre = intent.getStringExtra("nombreCurso") ?: "PROGRAMACIÓN AVANZADA"
        val codigo = intent.getStringExtra("codigoCurso") ?: "SIS101"
        val descripcion = intent.getStringExtra("descripcion") ?: "Programación orientada a objetos y estructuras de datos."
        val salon = intent.getStringExtra("salon") ?: "Laboratorio A-101"
        val totalHoras = intent.getStringExtra("totalHoras") ?: "80"
        val diasAtencion = intent.getStringExtra("diasAtencion") ?: "Lunes y Miércoles 08:00-10:00"

        // Mostrar la información en los TextViews
        tvTitulo.text = nombre
        tvCodigo.text = "Código: $codigo"
        tvDescripcion.text = descripcion
        tvSalon.text = salon
        tvHoras.text = "$totalHoras Horas"
        tvDias.text = diasAtencion
    }
}
