package com.rizky.exercise_project.data.api

import retrofit2.Call
import retrofit2.http.GET

interface MessageAPI {
    @GET("message")
    fun getMessages(): Call<MessagesResponse>
}