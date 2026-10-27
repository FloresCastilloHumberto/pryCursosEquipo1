package com.example.prycursosequipo1.InterfaceApi

import com.example.prycursosequipo1.Modelo.dataCursosInscripcion
import com.example.prycursosequipo1.Modelo.dataCurso
import com.example.prycursosequipo1.Modelo.dataCarrera
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    // Obtener todos los cursos
    @GET("cursos.php")
    suspend fun obtenerCursos(): dataCursosInscripcion

    // Obtener el detalle de un curso
    @GET("detalle_curso.php")
    suspend fun detalleCurso(@Query("idCurso") idCurso: Int): dataCurso

    // Obtener todas las carreras
    @GET("obtener_carrera.php")
    suspend fun obtenerCarreras(): List<dataCarrera>

    // Registrar una inscripción
    @POST("registro.php")
    suspend fun registrarInscripcion(@Body request: RegistroInscripcionRequest): RegistroInscripcionResponse

    @Serializable
    data class RegistroInscripcionRequest(
        @SerialName("matricula") val matricula: String = "",
        @SerialName("correo") val correo: String = "",
        @SerialName("idCurso") val idCurso: Int = 0,
        @SerialName("fechaRegistro") val fechaRegistro: String = ""
    )
    @Serializable
    data class RegistroInscripcionResponse(
        @SerialName("exito") val exito: Boolean = false,

        @SerialName("mensaje") val mensaje: String = ""
    )
}