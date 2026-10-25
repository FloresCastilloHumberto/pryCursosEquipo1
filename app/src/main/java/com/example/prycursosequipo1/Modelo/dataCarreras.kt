package com.example.prycursosequipo1.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataCarreras(
    @SerialName("idCarrera") val id: Int=0,
    @SerialName("nombreCarrera") val carrera: String=""
){
    override fun toString(): String {
        return carrera
    }
}
