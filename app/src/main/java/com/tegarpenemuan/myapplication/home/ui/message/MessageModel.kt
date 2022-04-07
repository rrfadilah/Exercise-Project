package com.tegarpenemuan.myapplication.home.ui.message

data class MessageModel(
    val id: String,
    val imageRes: Int,
    val image: String = "",
    val name: String,
    val lastMessage: String
)