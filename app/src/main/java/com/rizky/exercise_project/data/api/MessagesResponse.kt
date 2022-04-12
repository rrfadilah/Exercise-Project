package com.rizky.exercise_project.data.api

import com.google.gson.annotations.SerializedName

/**
 * com.rizky.exercise_project.data.api
 *
 * Created by Rizky Fadilah on 12/04/22.
 * https://github.com/rizkyfadilah
 *
 */

data class MessagesResponse(
    @SerializedName("message") var message: List<Message> = emptyList()
)

data class Message(
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("message") var message: String? = null
)
