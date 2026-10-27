package com.example.prycursosequipo1.Modelo

class clsCursosModelo {
    suspend fun obtenerCursosPorCarrera(carreraId: Int): List<dataCursos>{
        return RetrofitClient.apiService.obtenerCursos(carreraId).cursos
    }

    //Aqui va para la imagen
}