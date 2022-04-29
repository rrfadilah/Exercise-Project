package com.example.exercise_project.data.api.message

import retrofit2.Response
import retrofit2.http.*

interface MessageApi {
    @GET("data/message")
    suspend fun getMessages(): Response<List<MessagesResponse>>

    @POST("data/message")
    suspend fun postMessages(
        @Body request: MessagesRequest
    ): Response<MessagesResponse>

    @DELETE("data/message/{ID}")
    suspend fun deleteMessages(
        @Path("ID") id: String
    ): Response<Unit>

    @PUT("data/message/{ID}")
    suspend fun updateMessages(
        @Path("ID") id: String,
        @Body request: MessagesRequest,
    ): Response<MessagesResponse>
}