package net.mzhasanah.fiveinone.exerciseproject.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import androidx.activity.viewModels
import net.mzhasanah.fiveinone.exerciseproject.Constant
import net.mzhasanah.fiveinone.exerciseproject.ui.onboarding.OnBoardingActivity
import net.mzhasanah.fiveinone.exerciseproject.databinding.ActivityMainBinding
import net.mzhasanah.fiveinone.exerciseproject.home.HomeActivity

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
//        val handler = Handler()
//        handler.postDelayed({
//            startActivity(Intent(this, OnBoardingActivity::class.java))
//            finish()
//        }, 5000L)
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
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun bindView() {

    }
}