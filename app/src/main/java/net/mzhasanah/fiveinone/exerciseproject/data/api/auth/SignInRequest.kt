package net.mzhasanah.fiveinone.exerciseproject.data.api.auth

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("login") var login: String? = null,
    @SerializedName("password") var password: String? = null
)