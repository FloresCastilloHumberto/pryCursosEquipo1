package com.example.prycursosequipo1.Presentador

import android.util.Patterns
import com.example.prycursosequipo1.InterfaceApi.ApiService
import com.example.prycursosequipo1.Modelo.clsRegistroCursoModelo
import com.example.prycursosequipo1.Vista.activity_registro_cursos
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class clsRegistroPresentador(private val view: activity_registro_cursos) {

    private val modelo = clsRegistroCursoModelo()

    fun realizarRegistro(matricula: String, fecha: String, correo: String, idCurso: Int) {
        if (matricula.isEmpty() || fecha.isEmpty() || correo.isEmpty()) {
            view.mostrarError(
                "Por favor, completa todos los campos"
            )
            return
        }

        val correoLimpio = correo.trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(correoLimpio).matches()) {
            view.mostrarError("Ingresa un correo válido")
            return
        }

        if (idCurso <= 0) {
            view.mostrarError("No se recibió un curso válido")
            return
        }

        val request = ApiService.RegistroInscripcionRequest(matricula = matricula, correo = correoLimpio, idCurso = idCurso, fechaRegistro = fecha)

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = withContext(Dispatchers.IO) {
                    modelo.crearInscripcion(matricula, correoLimpio, idCurso, fecha)
                }

                if (respuesta.exito) {
                    view.registroExitoso(respuesta.mensaje)
                } else {
                    view.mostrarError(respuesta.mensaje)
                }

            } catch (e: Exception) {
                view.mostrarError(
                    "Error de conexión: ${e.localizedMessage ?: "Error desconocido"}"
                )
            }
        }
    }
}