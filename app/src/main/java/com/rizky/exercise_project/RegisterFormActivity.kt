package com.rizky.exercise_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rizky.exercise_project.databinding.ActivityRegisterFormBinding

class RegisterFormActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterFormBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val bundle = intent.extras
        val loginEmail = bundle?.getString("formEmail")
        val loginPassword = bundle?.getString("formPassword")

        if (bundle != null) {
            binding.formEmail.setText(loginEmail)
            binding.formPassword.setText(loginPassword)
        }

    }
}