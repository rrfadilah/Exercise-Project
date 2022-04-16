package com.rizky.exercise_project.data.api.auth

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

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
}