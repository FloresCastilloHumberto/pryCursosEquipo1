package com.example.prycursosequipo1.Presentador

import com.example.prycursosequipo1.Vista.EstadoInscripcionesActivity
import com.example.prycursosequipo1.Modelo.clsCarrerasModelo
import com.example.prycursosequipo1.Modelo.clsEstadoModelo
import com.example.prycursosequipo1.Modelo.dataCarreras
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsEstadoPresentador(val vista: EstadoInscripcionesActivity) {
    private val modeloEstado = clsEstadoModelo()
    private val modeloCarreras = clsCarrerasModelo()

    private var listaCarreras: List<dataCarreras> = emptyList()

    fun iniciar() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                listaCarreras = modeloCarreras.obtenerCarreras()
                vista.mostrarCarreras(listaCarreras)

                cargarEstado(0)

            } catch (e: Exception) {
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }

    fun seleccionarCarrera(posicion: Int) {
        if (posicion >= 0 && posicion < listaCarreras.size) {
            val carrera = listaCarreras[posicion]
            cargarEstado(carrera.id)
        }
    }

    fun cargarEstado(carreraId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val datos = modeloEstado.obtenerEstadoInscripciones(carreraId)
                vista.mostrarEstado(datos)

            } catch (e: Exception) {
                vista.mostrarError(
                    "Error al cargar las inscripciones: " + e.localizedMessage
                )
            }
        }
    }
}