package id.anantyan.exerciseproject.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataDummy(
    val email: String? = null,
    val password: String? = null
) : Parcelable
