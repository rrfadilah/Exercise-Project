package com.rizky.exercise_project.data.api

import retrofit2.Call
import retrofit2.http.GET

/**
 * com.rizky.exercise_project.data.api
 *
 * Created by Rizky Fadilah on 12/04/22.
 * https://github.com/rizkyfadilah
 *
 */

interface MessageAPI {

    @GET("message")
    fun getMessages(): Call<MessagesResponse>
}
