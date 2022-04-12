package com.tegarpenemuan.myapplication.data.api

import retrofit2.Call
import retrofit2.http.GET

interface MessageAPI {

    @GET("message")
    fun getMessage(): Call<MessagesResponse>
}