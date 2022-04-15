package com.example.exercise_project.data.API

import retrofit2.Call
import retrofit2.http.GET

interface MessageAPI {
    @GET("message")
    fun getMessages(): Call<MessagesResponse>
}