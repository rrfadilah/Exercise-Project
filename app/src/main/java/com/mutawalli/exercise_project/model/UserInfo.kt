package com.mutawalli.exercise_project.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
    val key: String,
    val phone: String,
    val email: String
): Parcelable