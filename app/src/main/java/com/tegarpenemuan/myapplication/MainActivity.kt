package com.tegarpenemuan.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, OnBoardingActivity::class.java)
            startActivity(intent)
        }, 3000)

        Log.d("Lifecycle","Lifecycle OnCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle","Lifecycle OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle","Lifecycle OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle","Lifecycle OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle","Lifecycle OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle","Lifecycle OnDestroy")
    }
}