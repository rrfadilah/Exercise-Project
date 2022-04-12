package id.anantyan.exerciseproject.data.api

import id.anantyan.exerciseproject.model.MessagesList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface MessagesApi {
    @GET("message")
    fun getMessage() : Call<MessagesList>
}