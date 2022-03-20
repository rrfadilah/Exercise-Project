package com.example.exercise_project

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager.widget.ViewPager
import com.afollestad.viewpagerdots.DotsIndicator

class OnBoardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        val btnSIgnUp = findViewById<Button>(R.id.btnGetStarted)
        val btnSIgnIn = findViewById<Button>(R.id.btnSignIn)

        btnSIgnUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        btnSIgnIn.setOnClickListener {
            val intent = Intent(this, SignActivity::class.java)
            startActivity(intent)
        }

        var imgs = listOf<Int>(R.drawable.bgonboarding,R.drawable.bgonboarding2,R.drawable.bgonboarding3,R.drawable.bgonboarding4,R.drawable.bgonboarding5)
        val adapter = OnBoardingAdapter(imgs,this)
        val dots: DotsIndicator = findViewById(R.id.dots)
        var viewPager = findViewById<ViewPager>(R.id.vp_on_boarding)
        viewPager.adapter = adapter
        dots.attachViewPager(viewPager)
        dots.setDotTint(Color.WHITE)

    }
}