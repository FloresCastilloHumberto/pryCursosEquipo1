package com.example.prycursosequipo1.Modelo

import com.example.prycursosequipo1.InterfaceApi.ApiService

class clsRegistroCursoModelo {
    suspend fun crearInscripcion(matricula: String, correo: String, idCurso: Int, fechaRegistro: String): ApiService.RegistroInscripcionResponse {
        val request = ApiService.RegistroInscripcionRequest(matricula = matricula, correo = correo, idCurso = idCurso, fechaRegistro = fechaRegistro)

        return RetrofitClient.apiService.registrarInscripcion(request)
    }

    // Buscar alumno por matrícula
    suspend fun buscarCorreo(matricula: String): ApiService.BuscarCorreoResponse {

        return RetrofitClient.apiService.buscarCorreo(matricula)
    }
}