package com.rizky.exercise_project.home.ui.message

/**
 * com.rizky.exercise_project.home.ui.message
 *
 * Created by Rizky Fadilah on 23/03/22.
 * https://github.com/rizkyfadilah
 *
 */

data class MessageModel(
    val id: String,
    val imageRes: Int,
    val image: String = "",
    val name: String,
    val lastMessage: String
)
