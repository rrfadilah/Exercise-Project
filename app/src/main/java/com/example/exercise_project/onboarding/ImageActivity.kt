package com.example.exercise_project.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.exercise_project.R
import com.example.exercise_project.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity() {
    lateinit var binding: ActivityImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listImage = listOf<Fragment>(
            Image1Fragment(),
            Image1Fragment(),
            Image1Fragment()
        )

        val adapter = AdapterImage(supportFragmentManager, listImage)
        binding.vpImage.adapter = adapter
    }
}