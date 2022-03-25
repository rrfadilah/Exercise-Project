package com.rizky.exercise_project.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val image: String? = null,
    val name: String? = null
) : Parcelable
