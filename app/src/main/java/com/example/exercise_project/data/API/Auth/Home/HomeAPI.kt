package com.example.exercise_project.data.API.Auth.Home

import retrofit2.Response
import retrofit2.http.GET

interface HomeAPI {
    @GET("hospital")
    suspend fun hospital(): Response<List<HospitalResponse>>
}