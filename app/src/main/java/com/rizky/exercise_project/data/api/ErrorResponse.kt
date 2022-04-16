package com.rizky.exercise_project.data.api

import com.google.gson.annotations.SerializedName

/**
 * com.rizky.exercise_project.data.api
 *
 * Created by Rizky Fadilah on 16/04/22.
 * https://github.com/rizkyfadilah
 *
 */

data class ErrorResponse(
    @SerializedName("code") var code: Int? = null,
    @SerializedName("message") var message: String? = null,
)
