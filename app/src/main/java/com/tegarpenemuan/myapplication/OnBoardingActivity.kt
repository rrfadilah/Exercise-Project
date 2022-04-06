package com.tegarpenemuan.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tegarpenemuan.myapplication.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {

    lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}