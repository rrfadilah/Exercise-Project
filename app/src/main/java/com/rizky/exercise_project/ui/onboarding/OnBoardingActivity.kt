package com.rizky.exercise_project.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.rizky.exercise_project.R
import com.rizky.exercise_project.databinding.ActivityOnBoardingBinding
import com.rizky.exercise_project.onboardingfragment.*
import com.rizky.exercise_project.ui.signin.LoginFormActivity
import com.rizky.exercise_project.ui.signup.RegisterFormActivity

class OnBoardingActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var getStarted: Button = findViewById(R.id.btnGetStarted)
        var signIn: Button = findViewById(R.id.btnSignIn)


        val listOnBoarding = listOf<Fragment>(
            OnBoardingFragment1(),
            OnBoardingFragment2(),
            OnBoardingFragment3(),
            OnBoardingFragment4()
        )

        val adapter = AdapterOnBoarding(supportFragmentManager, listOnBoarding)
        binding.ivOnBoarding.adapter = adapter

        getStarted.setOnClickListener {
            val intentRegister = Intent(this, RegisterFormActivity::class.java)
            startActivity(intentRegister)
        }

        signIn.setOnClickListener {
            val intenSignin = Intent(this, LoginFormActivity::class.java)
            startActivity(intenSignin)
        }
    }
}