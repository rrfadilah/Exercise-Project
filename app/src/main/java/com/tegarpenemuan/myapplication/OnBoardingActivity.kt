package com.tegarpenemuan.myapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.afollestad.viewpagerdots.DotsIndicator
import com.tegarpenemuan.myapplication.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {

    lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignIn.setOnClickListener {
            startActivity(Intent(this,SignActivity::class.java))
        }

        onBoarding()
    }

    private fun onBoarding() {
        val imgs = listOf(
            R.drawable.bg_onboarding,
            R.drawable.bg_onboarding,
            R.drawable.bg_onboarding
        )

        val adapter = OnBoardingAdapter(imgs, this)
        val dots: DotsIndicator = findViewById(R.id.dots)
        val viewPager = findViewById<ViewPager>(R.id.vp_on_boarding)
        viewPager.adapter = adapter
        dots.attachViewPager(viewPager)
        dots.setDotTint(Color.WHITE)
    }
}