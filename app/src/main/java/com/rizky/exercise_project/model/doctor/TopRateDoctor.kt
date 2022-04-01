package com.rizky.exercise_project.model.doctor

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TopRateDoctor(
    val image: String? = null,
    val doctorName: String? = null,
    val specialist: String? = null,
    val rating: Float? = null
) : Parcelable