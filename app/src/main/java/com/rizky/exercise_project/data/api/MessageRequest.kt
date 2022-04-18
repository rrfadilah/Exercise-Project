package com.rizky.exercise_project.data.api

import com.google.gson.annotations.SerializedName

data class MessageRequest (
    @SerializedName("message" ) var message : String? = null,
    @SerializedName("name"    ) var name    : String? = null,
    @SerializedName("image"   ) var image   : String? = null
)