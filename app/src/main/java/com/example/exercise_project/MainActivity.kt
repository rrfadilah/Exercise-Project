package com.example.exercise_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.exercise_project.galleryfragment.GalleryActivity
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        thread {
            Thread.sleep(5000)
            val intent = Intent(this, OnBoardingActivity::class.java)
            startActivity(intent)
        }

        Log.d("Lifecycle", "Lifecycle onCreate")

    }
    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "Lifecycle onStart")
    }
    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "Lifecycle onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "Lifecycle onPause")
    }
    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "Lifecycle onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "Lifecycle onDestroy")
    }
}