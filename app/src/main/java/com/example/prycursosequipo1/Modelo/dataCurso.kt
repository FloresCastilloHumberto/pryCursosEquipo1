package com.example.prycursosequipo1.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataCurso(
    @SerialName("idCurso") val idCurso: Int = 0,
    @SerialName("idCarrera") val idCarrera: Int = 0,
    @SerialName("nombreCurso") val nombreCurso: String = "",
    @SerialName("codigoCurso") val codigoCurso: String = "",
    @SerialName("descripcion") val descripcion: String = "",
    @SerialName("salon") val salon: String = "",
    @SerialName("totalHoras") val totalHoras: Int = 0,
    @SerialName("diasAtencion") val diasAtencion: String = "",
    @SerialName("cupoMaximo") val cupoMaximo: Int = 0,
    @SerialName("imagenCurso") val imagenCurso: String = ""
) {
    override fun toString(): String {
        return nombreCurso
    }
}
