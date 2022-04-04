package com.sulthoni.exercise_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sulthoni.exercise_project.databinding.ActivityOnBoardingBinding
import com.sulthoni.minichallangeexerciseproject.Fragment.FirstView
import com.sulthoni.exercise_project.fragment.FourthView
import com.sulthoni.exercise_project.fragment.SecondView
import com.sulthoni.exercise_project.fragment.ThirdView


class OnBoardingActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listView = listOf<Fragment>(
            FirstView(),
            SecondView(),
            ThirdView(),
            FourthView()
        )

        val adapter = OnBoardingAdapter(supportFragmentManager, listView)
        binding.vpImage.adapter = adapter


        binding.btnGetStarted.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignIn.setOnClickListener{
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}