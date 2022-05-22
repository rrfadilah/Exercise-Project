package net.mzhasanah.fiveinone.exerciseproject.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import net.mzhasanah.fiveinone.exerciseproject.*
import net.mzhasanah.fiveinone.exerciseproject.databinding.ActivityOnBoardingBinding
import net.mzhasanah.fiveinone.exerciseproject.ui.galleryfragment.AdapterGallery
import net.mzhasanah.fiveinone.exerciseproject.ui.signin.SignInActivity
import net.mzhasanah.fiveinone.exerciseproject.ui.signup.SignUpActivity

class OnBoardingActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetStarted.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        val listGallery = listOf<Fragment>(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment(),
            FourthFragment()
        )

        val adapter = AdapterGallery(supportFragmentManager, listGallery)
        binding.vpOnboarding.adapter = adapter

        Log.d("Lifecycle", "Lifecycle OnCreate")
    }

    fun ClickSignIn(V: View?) {
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "Lifecycle OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "Lifecycle OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "Lifecycle OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "Lifecycle OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "Lifecycle OnDestroy")
    }
}