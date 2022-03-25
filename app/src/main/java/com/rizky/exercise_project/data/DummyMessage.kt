package com.rizky.exercise_project.data

import com.rizky.exercise_project.R
import com.rizky.exercise_project.menu.ui.message.model.MessageModel

object DummyMessage {
        val messages: List<MessageModel> = listOf(
            MessageModel(
                avatar = R.drawable.img_user_1,
                name = "Nairobi Putri Hayza",
                desc = "Oh tentu saja tidak karena jeruk it..."
            ),
            MessageModel(
                avatar = R.drawable.img_user_1,
                name = "John McParker Steve",
                desc = "Oke menurut pak dokter bagaimana unt..."
            ),
            MessageModel(
                avatar = R.drawable.img_user_1,
                name = "Rizky Fadilah",
                desc = "Hallo gais semuanya disini"
            ),
        )
}