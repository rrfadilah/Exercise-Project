package net.mzhasanah.fiveinone.exerciseproject.ui.home.message

data class MessageModel(
    val id: String,
    val image: String = "",
    val imageRes: Int,
    val name: String,
    val lastMessage: String
)