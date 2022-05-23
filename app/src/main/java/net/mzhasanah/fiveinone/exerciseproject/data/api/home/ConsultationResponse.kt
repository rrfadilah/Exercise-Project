package net.mzhasanah.fiveinone.exerciseproject.data.api.home

import com.google.gson.annotations.SerializedName

data class ConsultationResponse(
    @SerializedName("id") var id: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("icon") var icon: String? = null
)
