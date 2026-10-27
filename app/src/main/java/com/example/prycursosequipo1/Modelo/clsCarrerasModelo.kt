package com.example.prycursosequipo1.Modelo

class clsCarrerasModelo {
    suspend fun obtenerCarreras(): List<dataCarreras>{
        val datos = RetrofitClient.apiService.obtenerCarreras()

        val listaFinal = mutableListOf(dataCarreras(0, "Todas las carreras"))
        listaFinal.addAll(datos)

        return listaFinal
    }
}