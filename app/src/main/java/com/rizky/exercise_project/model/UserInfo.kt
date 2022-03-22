package com.rizky.exercise_project.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * com.rizky.exercise_project.model
 *
 * Created by Rizky Fadilah on 21/03/22.
 * https://github.com/rizkyfadilah
 *
 */

@Parcelize
data class UserInfo(
    val key: String,
    val phone: String,
    val email: String
): Parcelable