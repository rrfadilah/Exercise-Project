package com.dzamir.exercise_project.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.dzamir.exercise_project.Constant
import com.dzamir.exercise_project.R
import com.dzamir.exercise_project.databinding.ActivityMainBinding
import com.dzamir.exercise_project.home.Home_activity
import com.dzamir.exercise_project.ui.onboarding.OnBoardingActivity
import com.google.android.ads.mediationtestsuite.activities.HomeActivity

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = this.getSharedPreferences(Constant.Preferences.PREF_NAME, MODE_PRIVATE)
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
                val intent = Intent(this, OnBoardingActivity::class.java)
                startActivity(intent)
            }
        }

        viewModel.shouldOpenHomePage.observe(this) {
            if (it) {
                val intent = Intent(this, Home_activity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun bindView() {

    }
}