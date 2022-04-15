package com.rizky.exercise_project.data.api

import com.google.gson.annotations.SerializedName

/**
 * com.rizky.exercise_project.data.api
 *
 * Created by Rizky Fadilah on 15/04/22.
 * https://github.com/rizkyfadilah
 *
 */

data class MessagesRequest(
    @SerializedName("message") var message: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("image") var image: String? = null
)
