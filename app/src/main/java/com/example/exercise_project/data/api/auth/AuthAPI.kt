package com.example.exercise_project.data.api.auth

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthAPI {
    @POST("users/login")
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>

    @POST("users/register")
    fun register(
        @Body request: RegisterRequest
    ): Call<RegisterResponse>
}