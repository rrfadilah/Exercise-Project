package com.kezia.exercise_project.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kezia.exercise_project.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}