package com.rizky.exercise_project.data.api

import com.google.gson.annotations.SerializedName

data class MessagesResponse (
    @SerializedName("message" ) var message : ArrayList<Message> = arrayListOf()
)

data class Message (
    @SerializedName("id"      ) var id      : String? = null,
    @SerializedName("name"    ) var name    : String? = null,
    @SerializedName("image"   ) var image   : String? = null,
    @SerializedName("message" ) var message : String? = null
)
