package com.example.prycursosequipo1.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class dataEstadoInscripciones {
    @SerialName("total") val total: Int = 0;
    @SerialName("cursos") val cursos: List<dataEstadoCurso> = emptyList()
}