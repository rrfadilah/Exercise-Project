package com.example.mydoctor.data.api

import com.google.gson.annotations.SerializedName

data class MessagesRequest(
    @SerializedName("message") var message: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("image") var image: String? = null
)