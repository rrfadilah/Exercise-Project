package com.rizky.exercise_project.model.doctor

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val title: String? = null,
    val calendar: String? = null,
    val image: String? = null
) : Parcelable
