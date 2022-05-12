package com.rizky.exercise_project.data.api.auth

import retrofit2.Response
import retrofit2.http.*

/**
 * com.rizky.exercise_project.data.api
 *
 * Created by Rizky Fadilah on 16/04/22.
 * https://github.com/rizkyfadilah
 *
 */

interface AuthAPI {
    @POST("users/login")
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>

    @POST("users/register")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>

    @GET("users/logout")
    suspend fun logout(@HeaderMap headers: Map<String, String>): Response<Unit>
}
