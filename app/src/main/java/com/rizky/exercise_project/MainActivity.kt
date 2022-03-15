package com.rizky.exercise_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Lifecycle", "Lirecycle OnCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "Lirecycle OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "Lirecycle OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "Lirecycle OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "Lirecycle OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "Lirecycle OnDestroy")
    }
}