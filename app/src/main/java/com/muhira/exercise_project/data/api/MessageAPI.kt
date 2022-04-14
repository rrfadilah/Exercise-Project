package com.nuhira.exercise_project.data.api

import com.muhira.exercise_project.data.api.MessagesResponse
import retrofit2.Call
import retrofit2.http.GET



interface MessageAPI {

    @GET("message")
    fun getMessages(): Call<MessagesResponse>
}
