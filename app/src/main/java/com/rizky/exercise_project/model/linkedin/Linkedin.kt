package com.rizky.exercise_project.model.linkedin

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Linkedin(
    val id_post: Int? = null,
    val img_picture_person: String? = null,
    val img_profile_picture: String? = null,
    val like_person: Int? = null,
    val name_person: String? = null,
    val motto: String? = null,
    val last_send: String? = null,
    val description: String? = null,
    val img_post: String? = null,
    val img_reaction: Int? = null,
    val reaction: Int? = null,
    val like_comment: String? = null
) : Parcelable
