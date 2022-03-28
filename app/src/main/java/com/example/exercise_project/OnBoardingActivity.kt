package com.example.exercise_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.exercise_project.databinding.ActivityOnBoardingBinding
import com.example.exercise_project.onboarding.*

//class OnBoardingActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_on_boarding)
//    }
//}

class OnBoardingActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listImage = listOf<Fragment>(
            Image1Fragment(),
            Image2Fragment(),
            Image3Fragment(),
            Image4Fragment()
        )

        val adapter = AdapterImage(supportFragmentManager, listImage)
        binding.vpImage.adapter = adapter

        binding.btnGetStarted.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignIn.setOnClickListener {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }
    }
}

