package com.kezia.exercise_project.data.api.auth

import com.google.gson.annotations.SerializedName

/**
 * com.rizky.exercise_project.data.api.auth
 *
 * Created by Rizky Fadilah on 16/04/22.
 * https://github.com/rizkyfadilah
 *
 */

data class SignInRequest(
    @SerializedName("login") var login: String? = null,
    @SerializedName("password") var password: String? = null
)