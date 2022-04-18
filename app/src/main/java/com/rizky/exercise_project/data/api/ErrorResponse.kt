package com.rizky.exercise_project.data.api

import com.google.gson.annotations.SerializedName

data class ErrorResponse (
    @SerializedName("code") var code: Int? = null,
    @SerializedName("message") var message: String? = null,
)