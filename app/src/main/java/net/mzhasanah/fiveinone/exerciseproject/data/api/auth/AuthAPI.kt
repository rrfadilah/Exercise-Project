package net.mzhasanah.fiveinone.exerciseproject.data.api.auth

import retrofit2.Response
import retrofit2.http.*

interface AuthAPI {
    @POST("users/login")
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>

    @POST("users/register")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>

    @GET("users/logout")
    suspend fun logout(@HeaderMap headers: Map<String, String>): Response<LogoutResponse>
}