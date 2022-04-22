package com.example.exercise_project.data.api.auth

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("name"  ) var name  : String? = null,
    @SerializedName("email" ) var email : String? = null,
    @SerializedName("job"   ) var job   : String? = null,
    @SerializedName("image" ) var image : String? = null
)
