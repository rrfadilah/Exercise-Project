package com.example.exercise_project.ui.home.ui.messages

import android.accounts.AuthenticatorDescription

data class MessageModel(
    val id: String,
    val imageRes: Int,
    val image: String = "",
    val name: String,
    val lastMessage: String
)