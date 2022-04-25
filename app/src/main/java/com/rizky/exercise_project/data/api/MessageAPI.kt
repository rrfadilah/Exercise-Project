package com.rizky.exercise_project.data.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

/**
 * com.rizky.exercise_project.data.api
 *
 * Created by Rizky Fadilah on 12/04/22.
 * https://github.com/rizkyfadilah
 *
 */

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
