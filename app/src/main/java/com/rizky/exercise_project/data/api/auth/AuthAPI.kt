package com.rizky.exercise_project.data.api.auth

import com.rizky.exercise_project.data.api.auth.register.RegisterRequest
import com.rizky.exercise_project.data.api.auth.register.RegisterResponse
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