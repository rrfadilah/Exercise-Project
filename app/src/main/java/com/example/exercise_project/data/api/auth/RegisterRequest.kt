package com.example.exercise_project.data.api.auth

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("name"     ) var name     : String? = null,
    @SerializedName("email"    ) var email    : String? = null,
    @SerializedName("job"      ) var job      : String? = null,
    @SerializedName("password" ) var password : String? = null
)
