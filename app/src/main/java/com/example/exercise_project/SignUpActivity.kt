package com.example.exercise_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exercise_project.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    companion object{
        const val INTENT_EMAIL = "inputEmail"
        const val INTENT_PASSWORD = "inputPassword"
    }

    lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_sign_up)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val inputEmail = bundle?.getString(INTENT_EMAIL)
        val inputPassword = bundle?.getString(INTENT_PASSWORD)

        if (bundle != null){
            binding.inputEmail.setText(inputEmail)
            binding.inputPassword.setText(inputPassword)
        }

    }
}