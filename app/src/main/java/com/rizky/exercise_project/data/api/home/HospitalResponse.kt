package com.rizky.exercise_project.data.api.home

import com.google.gson.annotations.SerializedName

data class HospitalResponse (
    @SerializedName("objectId") var objectId: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("address") var address: String? = null
)