package com.example.prycursosequipo1.Presentador

import com.example.prycursosequipo1.Modelo.dataCarreras
import com.example.prycursosequipo1.Vista.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.prycursosequipo1.Modelo.clsCarrerasModelo
import com.example.prycursosequipo1.Modelo.clsCursosModelo
import com.example.prycursosequipo1.Modelo.dataCursos

class clsCarrerasPresentador(val vista: MainActivity) {
    private val modeloCarreras = clsCarrerasModelo()
    private val modeloCursos = clsCursosModelo()
    private var listaCarreras: List<dataCarreras> = emptyList()

    fun iniciar(){
        CoroutineScope(Dispatchers.Main).launch {
            try{
                listaCarreras = modeloCarreras.obtenerCarreras()
                vista.mostrarCarreras(listaCarreras)

                cargarCurso(0)
            }catch (e: Exception){
                vista.mostrarError("Error: "+e.localizedMessage)
            }
        }
    }

    fun seleccionarCarrera(posicion: Int){
        if(posicion>=0 && posicion<listaCarreras.size){
            val carrera = listaCarreras[posicion]
            cargarCurso(carrera.id)
        }
    }

    fun cargarCurso(categoriaId: Int){
        CoroutineScope(Dispatchers.Main).launch {
            try{
                val cursos = modeloCursos.obtenerCursosPorCarrera(categoriaId)
                vista.mostrarCursos(cursos)
            }catch (e: Exception){
                vista.mostrarError("Error al cargar los cursos: "+e.localizedMessage)
            }
        }
    }

    //Aqui va lo relacionado a detalle curso

}