package com.example.prycursosequipo1.Vista

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prycursosequipo1.Adaptador.EstadoAdaptador
import com.example.prycursosequipo1.Modelo.dataCarreras
import com.example.prycursosequipo1.Modelo.dataEstadoInscripciones
import com.example.prycursosequipo1.Presentador.clsEstadoPresentador
import com.example.prycursosequipo1.R

class EstadoInscripcionesActivity : AppCompatActivity() {
    private lateinit var presentador: clsEstadoPresentador
    private lateinit var spinner: Spinner
    private lateinit var adaptador: EstadoAdaptador
    private lateinit var recyclerEstado: RecyclerView
    private lateinit var txtTotal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estado_inscripciones)

        spinner = findViewById(R.id.spnCarrerasEstado)
        recyclerEstado = findViewById(R.id.rvEstado)
        txtTotal = findViewById(R.id.txtTotal)

        recyclerEstado.layoutManager = LinearLayoutManager(this)
        adaptador = EstadoAdaptador(emptyList())
        recyclerEstado.adapter = adaptador

        presentador = clsEstadoPresentador(this)

        configurarListeners()
        presentador.iniciar()
    }
    private fun  configurarListeners(){
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
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
        findViewById<TextView>(R.id.btnAtras).setOnClickListener {
            finish()
        }
        findViewById<View>(R.id.btnVolverCatalogo).setOnClickListener {
            finish()
        }
    }
    fun mostrarCarreras(datos: List<dataCarreras>){
        val adapter= ArrayAdapter(this, R.layout.item_spinner, datos)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter=adapter
    }
    // Mostrar los registros de inscripciones
    fun mostrarEstado(datos: dataEstadoInscripciones) {
        txtTotal.text = "Total: ${datos.total}"
        adaptador.actualizarLista(datos.cursos)
    }
    fun mostrarError(mensaje: String){
        Toast.makeText(
            this,
            mensaje,
            Toast.LENGTH_LONG
        ).show()
    }
}