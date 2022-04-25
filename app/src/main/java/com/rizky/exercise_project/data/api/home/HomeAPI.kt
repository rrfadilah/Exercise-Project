package com.rizky.exercise_project.data.api.home

import retrofit2.Response
import retrofit2.http.*

/**
 * com.rizky.exercise_project.data.api
 *
 * Created by Rizky Fadilah on 16/04/22.
 * https://github.com/rizkyfadilah
 *
 */

interface HomeAPI {
    @GET("consultations")
    suspend fun consultations(): Response<List<ConsultationResponse>>

    @GET("toprates")
    suspend fun toprates(): Response<List<TopRatedResponse>>

    @GET("goodnews")
    suspend fun goodnews(): Response<List<GoodNewsResponse>>
}
