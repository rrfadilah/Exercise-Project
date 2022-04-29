package com.example.exercise_project.ui.onboarading

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.afollestad.viewpagerdots.DotsIndicator
import com.example.exercise_project.R
import com.example.exercise_project.ui.home.HomeActivity
import com.example.exercise_project.ui.signin.SignActivity

class OnBoardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        val btnSIgnUp = findViewById<Button>(R.id.btnGetStarted)
        val btnSIgnIn = findViewById<Button>(R.id.btnSignIn)

        btnSIgnUp.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        btnSIgnIn.setOnClickListener {
            val intent = Intent(this, SignActivity::class.java)
            startActivity(intent)
        }

        val imgs = listOf(
            R.drawable.bgonboarding,
            R.drawable.bgonboarding2,
            R.drawable.bgonboarding3,
            R.drawable.bgonboarding4,
            R.drawable.bgonboarding5
        )
        val adapter = OnBoardingAdapter(imgs, this)
        val dots: DotsIndicator = findViewById(R.id.dots)
        val viewPager = findViewById<ViewPager>(R.id.vp_on_boarding)
        viewPager.adapter = adapter
        dots.attachViewPager(viewPager)
        dots.setDotTint(Color.WHITE)

    }
}