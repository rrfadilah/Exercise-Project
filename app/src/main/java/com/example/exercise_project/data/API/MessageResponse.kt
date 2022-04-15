package com.example.exercise_project.data.API

import com.google.gson.annotations.SerializedName

data class MessagesResponse(
    @SerializedName("objectId") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("message") var message: String? = null
)