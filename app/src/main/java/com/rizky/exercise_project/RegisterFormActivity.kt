package com.rizky.exercise_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rizky.exercise_project.databinding.ActivityRegisterFormBinding

class RegisterFormActivity : AppCompatActivity() {

    companion object{
        const val INTENT_EMAIL = "formEmail"
        const val INTENT_PASSWORD = "formPassword"
    }

    lateinit var binding: ActivityRegisterFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterFormBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val bundle = intent.extras
        val loginEmail = bundle?.getString(INTENT_EMAIL)
        val loginPassword = bundle?.getString(INTENT_PASSWORD)

        if (bundle != null) {
            binding.formEmail.setText(loginEmail)
            binding.formPassword.setText(loginPassword)
        }

        // intent data juga bisa kita store di Constant, lalu dipanggil seperti dibawah
        intent.extras?.getString(Constant.Intent.KEY)

    }
}