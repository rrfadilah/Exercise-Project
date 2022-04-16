package com.tegarpenemuan.myapplication.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.viewModels
import com.tegarpenemuan.myapplication.Constant
import com.tegarpenemuan.myapplication.databinding.ActivityMainBinding
import com.tegarpenemuan.myapplication.home.ui.HomeActivity
import com.tegarpenemuan.myapplication.ui.onboarding.OnBoardingActivity

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
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun bindView() {

    }

//    override fun onStart() {
//        super.onStart()
//
//        val registerPreferences =
//            this.getSharedPreferences(Constant.Register.PREF_REGISTER_NAME, MODE_PRIVATE)
//
//        binding.pbSplash.visibility =View.VISIBLE
//        Handler(Looper.getMainLooper()).postDelayed({
//            if (!registerPreferences.contains(Constant.Register.KEY.NAMA)) {
//                binding.pbSplash.visibility =View.GONE
//                val intent = Intent(this, OnBoardingActivity::class.java)
//                startActivity(intent)
//            } else {
//                if (!registerPreferences.getBoolean(Constant.Register.KEY.LOGIN,false)) {
//                    binding.pbSplash.visibility =View.GONE
//                    val intent = Intent(this, SignInActivity::class.java)
//                    startActivity(intent)
//                } else {
//                    binding.pbSplash.visibility =View.GONE
//                    val intent = Intent(this, HomeActivity::class.java)
//                    startActivity(intent)
//                }
//            }
//        }, 3000)
//        Log.d("Lifecycle", "Lifecycle OnStart")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.d("Lifecycle", "Lifecycle OnResume")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d("Lifecycle", "Lifecycle OnPause")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d("Lifecycle", "Lifecycle OnStop")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d("Lifecycle", "Lifecycle OnDestroy")
//    }
}