package com.example.exercise_project.UI.onBoarding

import Transform
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exercise_project.R
import com.example.exercise_project.databinding.ActivityOnBoardingBinding

class onBoardingActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listOnBoarding = listOf(
            onBoardingFragment(),
            onBoardingFragmentSecond(),
            onBoardingFragmentThird(),
            onBoardingFragmentFourth()
        )

        val adapter = onBoardingAdapter(supportFragmentManager, listOnBoarding)
        binding.vpOnBoarding.adapter = adapter
        binding.tlIndicator.setViewPager(binding.vpOnBoarding)
        binding.vpOnBoarding.setPageTransformer(true,Transform())
    }
}
