package com.example.exercise_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exercise_project.databinding.ActivitySignInBinding

class SignIn : AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val email = binding.inputEmail.text
        val password = binding.inputPassword.text

        binding.btnSignIn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            intent.putExtra("inputEmail", email)
            intent.putExtra("inputPassword", password)
            startActivity(intent)
        }

        binding.tvCreateNewAccount.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}