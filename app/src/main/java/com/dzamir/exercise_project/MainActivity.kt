package com.dzamir.exercise_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dzamir.exercise_project.ui.splashscreen.SplashActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, SplashActivity::class.java)
        startActivity(intent)
        finish()
        Log.d("Lifecycle", "Lifecycle OnCreate" )
    }
//
//    override fun onStart() {
//        super.onStart()
//        Log.d("Lifecycle", "Lifecycle onStart" )
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.d("Lifecycle", "Lifecycle OnResume" )
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d("Lifecycle", "Lifecycle OnPause" )
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d("Lifecycle", "Lifecycle OnStop" )
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d("Lifecycle", "Lifecycle OnDestroy" )
//    }

}