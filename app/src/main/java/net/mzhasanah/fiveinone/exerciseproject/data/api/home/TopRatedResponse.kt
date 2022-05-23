package net.mzhasanah.fiveinone.exerciseproject.data.api.home

import com.google.gson.annotations.SerializedName

data class TopRatedResponse(
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("specialist") var specialist: String? = null,
    @SerializedName("rating") var rating: Double? = null,
    @SerializedName("is_online") var isOnline: Boolean? = null
)