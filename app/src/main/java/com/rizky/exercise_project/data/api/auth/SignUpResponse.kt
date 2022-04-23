package com.rizky.exercise_project.data.api.auth

import com.google.gson.annotations.SerializedName

/**
 * com.rizky.exercise_project.data.api.auth
 *
 * Created by Rizky Fadilah on 16/04/22.
 * https://github.com/rizkyfadilah
 *
 */

data class SignUpResponse(
    @SerializedName("name") var name: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("job") var job: String? = null,
    @SerializedName("image") var image: String? = null
)
