package com.example.prycursosequipo1.Modelo

class clsEstadoModelo {

    suspend fun obtenerEstadoInscripciones(carreraId: Int): dataEstadoInscripciones {
        return RetrofitClient.apiService.obtenerEstadoInscripciones(carreraId)
    }
}