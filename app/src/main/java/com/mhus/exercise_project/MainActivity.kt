package com.mhus.exercise_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Lifecycle","Lifecycle Oncreat")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle","Lifecycle Onstrart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle","Lifecycle Onresume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle","Lifecycle Onpause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle","Lifecycle Onstop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle","Lifecycle Ondestroy")
    }
}