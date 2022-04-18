package com.rizky.exercise_project.data.api

import retrofit2.Call
import retrofit2.http.*

interface MessageAPI {
    @GET("data/message")
    fun getMessages(): Call<List<MessageResponse>>

    @POST("data/message")
    fun postMessages(
        @Body request: MessageRequest
    ): Call<MessageResponse>

    @DELETE("data/message/{ID}")
    fun deleteMessages(
        @Path("ID") id: String
    ): Call<Unit>

    @PUT("data/message/{ID}")
    fun updateMessages(
        @Path("ID") id: String,
        @Body request: MessageRequest,
    ): Call<MessageResponse>

}