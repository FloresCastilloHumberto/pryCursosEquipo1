package com.example.prycursosequipo1.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataCursos(
    @SerialName("idCurso") val id: Int=0,
    @SerialName("idCarrera") val carreraId: Int=0,
    @SerialName("nombreCurso") val nombre: String="",
    @SerialName("codigoCurso") val codigo: String="",
    @SerialName("descripcion") val descripcion: String="",
    @SerialName("salon") val salon: String="",
    @SerialName("totalHoras") val totalHoras: Int=0,
    @SerialName("diasAtencion") val diasAtencion: String="",
    @SerialName("cupoMaximo") val cupoMaximo: Int=0,
    @SerialName("imagenCurso") val urlImagenPrincipal: String=""
)
