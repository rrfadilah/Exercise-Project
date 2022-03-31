package com.rizky.exercise_project.LinkedIn

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.rizky.exercise_project.R
import com.rizky.exercise_project.databinding.ActivityLinkedinBinding
import com.rizky.exercise_project.databinding.ActivityMainBinding

class LinkedInActivity: AppCompatActivity() {
    lateinit var binding: ActivityLinkedinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linkedin)
        binding = ActivityLinkedinBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}