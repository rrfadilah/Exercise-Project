package com.rizky.exercise_project

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.rizky.exercise_project.menu.ui.message.MessageAdapter
import com.rizky.exercise_project.menu.ui.message.MessageModel

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val messages: List<MessageModel> = listOf(
            MessageModel(
                avatar = "https://i.ibb.co/zJHYGBP/binarlogo.jpg",
                name = "Rizky Fadilah",
                desc = "Hallo gais semuanya disini",
//                time = "12.06",
//                badges = "100"
            ),
            MessageModel(
                avatar = "https://www.its.ac.id/wp-content/uploads/2021/10/kampus-merdeka.png",
                name = "Abdul Hafiz",
                desc = "Mau isi aapa disini boleh aja ya teman teman semuanya",
//                time = "15.06",
//                badges = "5"
            ),
        )

//        val recyclerView: RecyclerView = findViewById(R.id.rv_message)
//        val adapter = MessageAdapter(messages)
//        recyclerView.adapter = adapter
    }
}