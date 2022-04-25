package com.rizky.exercise_project.data.api.home

import com.google.gson.annotations.SerializedName

/**
 * com.rizky.exercise_project.data.api.home
 *
 * Created by Rizky Fadilah on 25/04/22.
 * https://github.com/rizkyfadilah
 *
 */

data class TopRatedResponse(
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("specialist") var specialist: String? = null,
    @SerializedName("rating") var rating: Double? = null,
    @SerializedName("is_online") var isOnline: Boolean? = null
)
