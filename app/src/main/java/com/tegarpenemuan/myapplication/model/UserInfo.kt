package com.tegarpenemuan.myapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserInfo(
    val key: String,
    val phone: String,
    val email: String
): Parcelable