package com.example.exercise_project.UI.Splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import androidx.activity.viewModels
import com.example.exercise_project.R
import com.example.exercise_project.UI.Data
import com.example.exercise_project.UI.Home.ActivityForHome
import com.example.exercise_project.databinding.ActivitySplashBinding
import com.example.exercise_project.UI.onBoarding.onBoardingActivity

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = this.getSharedPreferences(Data.Preferences.PREF_NAME, MODE_PRIVATE)
        val timer = object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                viewModel.onViewLoaded(pref)
            }
        }
        timer.start()

        bindViewModel()
        bindView()
    }

    private fun bindViewModel() {
        viewModel.shouldOpenOnBoarding.observe(this) {
            if (it) {
                val intent = Intent(this, onBoardingActivity::class.java)
                startActivity(intent)
            }
        }

        viewModel.shouldOpenHomePage.observe(this) {
            if (it) {
                val intent = Intent(this, ActivityForHome::class.java)
                startActivity(intent)
            }
        }
    }

    private fun bindView() {

    }
}