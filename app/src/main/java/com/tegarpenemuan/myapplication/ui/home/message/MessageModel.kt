package com.tegarpenemuan.myapplication.ui.home.message

data class MessageModel(
    val id: String,
    val imageRes: Int,
    val image: String = "",
    val name: String,
    val lastMessage: String
)