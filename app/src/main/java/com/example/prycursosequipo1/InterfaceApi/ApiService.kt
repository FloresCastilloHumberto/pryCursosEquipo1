package com.example.prycursosequipo1.InterfaceApi

import com.example.prycursosequipo1.Modelo.dataCarreras
import com.example.prycursosequipo1.Modelo.dataCursos
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("obtener_carrera.php") suspend fun obtenerCarreras(): List<dataCarreras>
    @GET("cursos.php") suspend fun obtenerCursos(@Query("idCarrera") carreraId: Int): List<dataCursos>
}