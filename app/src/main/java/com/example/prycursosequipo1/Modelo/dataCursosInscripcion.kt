package com.example.prycursosequipo1.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataCursosInscripcion(
    @SerialName("exito") val exito: Boolean = false,
    @SerialName("cursos") val cursos: List<dataCurso> = emptyList(),
    @SerialName("mensaje") val mensaje: String = ""
)