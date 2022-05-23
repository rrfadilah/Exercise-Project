package net.mzhasanah.fiveinone.exerciseproject.data.api.home

import com.google.gson.annotations.SerializedName

data class GoodNewsResponse(
    @SerializedName("id") var id: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("date") var date: String? = null
)