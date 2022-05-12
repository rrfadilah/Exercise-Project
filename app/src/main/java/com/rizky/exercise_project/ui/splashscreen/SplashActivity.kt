package com.rizky.exercise_project.ui.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.rizky.exercise_project.Constant
import com.rizky.exercise_project.databinding.ActivityMainBinding
import com.rizky.exercise_project.datastore.AuthDataStoreManager
import com.rizky.exercise_project.repository.AuthRepository
import com.rizky.exercise_project.ui.home.HomeActivity
import com.rizky.exercise_project.ui.onboarding.OnBoardingActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: SplashViewModel by viewModels {
        SplashViewModel.Factory(
            repository = AuthRepository(AuthDataStoreManager(this))
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = this.getSharedPreferences(Constant.Preferences.PREF_NAME, MODE_PRIVATE)
        val timer = object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
//                viewModel.onViewLoaded(pref)
                viewModel.onViewLoaded()
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
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        viewModel.shouldOpenHomePage.observe(this) {
            if (it) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

//        viewModel.shouldGetToken.observe(this) {
//            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
//            Log.d("TOKEN", it)
//        }
    }

    private fun bindView() {

    }
}