package com.example.prycursosequipo1.InterfaceApi

import com.example.prycursosequipo1.Modelo.dataCarreras
import com.example.prycursosequipo1.Modelo.dataCursos
import com.example.prycursosequipo1.Modelo.dataCursosInscripcion
import com.example.prycursosequipo1.Modelo.dataEstadoInscripciones
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    // Obtener todos los cursos
    @GET("cursos.php")
    suspend fun obtenerCursos(@Query("idCarrera") carreraId: Int): dataCursosInscripcion

    // Obtener el detalle de un curso
    @GET("detalle_curso.php")
    suspend fun detalleCurso(@Query("idCurso") idCurso: Int): dataCursos

    // Obtener todas las carreras
    @GET("obtener_carrera.php")
    suspend fun obtenerCarreras(): List<dataCarreras>

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
    // VER REGISTROS
    @GET("obtener_estado_inscripciones.php")
    suspend fun obtenerEstadoInscripciones(
        @Query("carrera_id") carreraId: Int
    ): dataEstadoInscripciones
}