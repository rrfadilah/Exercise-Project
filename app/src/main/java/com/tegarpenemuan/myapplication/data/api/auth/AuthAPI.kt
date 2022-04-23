package com.tegarpenemuan.myapplication.data.api.auth

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {
    @POST("users/login")
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>

    @POST("users/register")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>
}