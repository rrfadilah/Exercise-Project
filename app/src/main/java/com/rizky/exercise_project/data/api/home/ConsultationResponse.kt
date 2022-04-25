package com.rizky.exercise_project.data.api.home

import com.google.gson.annotations.SerializedName

/**
 * com.rizky.exercise_project.data.api.home
 *
 * Created by Rizky Fadilah on 25/04/22.
 * https://github.com/rizkyfadilah
 *
 */

data class ConsultationResponse(
    @SerializedName("id") var id: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("icon") var icon: String? = null
)
