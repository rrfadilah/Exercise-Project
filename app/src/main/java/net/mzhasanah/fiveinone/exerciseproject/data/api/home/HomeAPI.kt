package net.mzhasanah.fiveinone.exerciseproject.data.api.home

import retrofit2.Response
import retrofit2.http.GET

interface HomeAPI {
    @GET("consultations")
    suspend fun consultations(): Response<List<ConsultationResponse>>

    @GET("toprates")
    suspend fun toprates(): Response<List<TopRatedResponse>>

    @GET("goodnews")
    suspend fun goodnews(): Response<List<GoodNewsResponse>>

    @GET("data/hospital")
    suspend fun getHospital(): Response<List<HospitalResponse>>
}