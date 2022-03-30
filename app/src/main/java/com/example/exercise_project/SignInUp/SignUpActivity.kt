package com.example.exercise_project.SignInUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exercise_project.R
import com.example.exercise_project.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.extras?.getString(Data.EMAIL)
        val pass = intent.extras?.getString(Data.PASS)

        binding.etEmailSignUp.setText(email)
        binding.etPassSignUp.setText(pass)

        binding.ibBack.setOnClickListener {
            val intentBack = Intent(this, SignInActivity::class.java)
            startActivity(intentBack)
        }
    }
}