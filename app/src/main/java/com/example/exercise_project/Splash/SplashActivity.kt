package com.example.exercise_project.Splash

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.exercise_project.R
import com.example.exercise_project.databinding.ActivitySplashBinding
import com.example.exercise_project.onBoardingFragment.onBoardingActivity

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler = Handler()
        handler!!.postDelayed({
            val intent = Intent(this, onBoardingActivity::class.java)
            startActivity(intent)
            finish()
        },5000)
    }
}