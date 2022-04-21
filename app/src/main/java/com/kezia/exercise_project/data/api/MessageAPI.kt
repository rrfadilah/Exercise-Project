package com.kezia.exercise_project.data.api

import retrofit2.Call
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
    fun getMessages(): Call<List<MessagesResponse>>

    @POST("data/message")
    fun postMessages(
        @Body request: MessagesRequest
    ): Call<MessagesResponse>

    @DELETE("data/message/{ID}")
    fun deleteMessages(
        @Path("ID") id: String
    ): Call<Unit>

    @PUT("data/message/{ID}")
    fun updateMessages(
        @Path("ID") id: String,
        @Body request: MessagesRequest,
    ): Call<MessagesResponse>

}
