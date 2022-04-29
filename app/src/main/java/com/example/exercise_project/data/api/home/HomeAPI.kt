package com.example.exercise_project.data.api.home

import retrofit2.Response
import retrofit2.http.GET

interface HomeAPI {
    @GET("data/hospital")
    suspend fun hospital(): Response<List<NearbyHospitalResponse>>

    @GET("consultations")
    suspend fun consultations(): Response<List<ConsultationResponse>>

    @GET("toprates")
    suspend fun toprates(): Response<List<TopRatedResponse>>

    @GET("goodnews")
    suspend fun goodnews(): Response<List<GoodNewsResponse>>
}