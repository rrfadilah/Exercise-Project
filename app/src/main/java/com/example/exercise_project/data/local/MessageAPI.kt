package com.example.exercise_project.data.local

import com.example.exercise_project.data.api.message.MessagesRequest
import com.example.exercise_project.data.api.message.MessagesResponse
import retrofit2.Response
import retrofit2.http.*

interface MessageAPI {

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
