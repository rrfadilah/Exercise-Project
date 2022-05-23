package net.mzhasanah.fiveinone.exerciseproject.ui.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import net.mzhasanah.fiveinone.exerciseproject.Constant
import net.mzhasanah.fiveinone.exerciseproject.ui.onboarding.OnBoardingActivity
import net.mzhasanah.fiveinone.exerciseproject.databinding.ActivityMainBinding
import net.mzhasanah.fiveinone.exerciseproject.datastore.AuthDataStoreManager
import net.mzhasanah.fiveinone.exerciseproject.repository.AuthRepository
import net.mzhasanah.fiveinone.exerciseproject.ui.home.HomeActivity
import javax.inject.Inject
import javax.inject.Named

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    @Inject
    @Named("TestString2")
    lateinit var stringDI: String

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
        binding.tvTitle.text = stringDI
    }
}