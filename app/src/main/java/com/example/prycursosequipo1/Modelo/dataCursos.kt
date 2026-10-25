package com.example.prycursosequipo1.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataCursos(
    @SerialName("id") val id: Int=0,
    @SerialName("carrera_id") val carreraId: Int=0,
    @SerialName("nombre") val nombre: String="",
    @SerialName("codigo") val codigo: String="",
    @SerialName("descripcion") val descripcion: String="",
    @SerialName("salon") val salon: String="",
    @SerialName("total_horas") val totalHoras: Int=0,
    @SerialName("dias_atencion") val diasAtencion: String="",
    @SerialName("cupo_maximo") val cupoMaximo: Int=0,
    @SerialName("url_imagen_principal") val urlImagenPrincipal: String=""
)
