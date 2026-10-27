package com.example.prycursosequipo1.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataCarrera(
    @SerialName("idCarrera") val idCarrera: Int = 0,
    @SerialName("nombreCarrera") val nombreCarrera: String = ""
) {
    override fun toString(): String {
        return nombreCarrera
    }
}
