package com.rizky.exercise_project.hospitals

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModelHospital(
    var Image: Int,
    var Title: String,
    var Description: String
): Parcelable

