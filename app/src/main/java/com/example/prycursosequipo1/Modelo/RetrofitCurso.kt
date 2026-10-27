package com.example.prycursosequipo1.Modelo

import com.example.prycursosequipo1.InterfaceApi.ApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlin.lazy
import retrofit2.Retrofit


object RetrofitCurso {
    private const val BASE_URL = "http://192.168.110.206/cursos_api/"

    private val jsonConfig = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        isLenient = true
    }

    val apiService: ApiService by lazy {
        val contentType = "application/json".toMediaType()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                jsonConfig.asConverterFactory(contentType)
            )
            .build()
            .create(ApiService::class.java)
    }
}