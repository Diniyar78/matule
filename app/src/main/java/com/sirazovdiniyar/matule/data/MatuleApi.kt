package com.sirazovdiniyar.matule.data

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface MatuleApi {

    @POST("collections/users/auth-with-password")
    suspend fun authWithPassword(
        @Body request: AuthRequest
    ): AuthResponse

    @GET("collections/users/records/{userid}")
    suspend fun getUser(
        @Header("Authorization") token: String,
        @Path("userid") userId: String
    ): User

}

val json = Json { ignoreUnknownKeys = true }

val matuleApi = Retrofit.Builder()
    .baseUrl("http://77.239.125.32:8090/api/")
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .build()
    .create(MatuleApi::class.java)