package com.rizky.exercise_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.rizky.exercise_project.databinding.ActivityLoginFormBinding

class LoginFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginFormBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val email = binding.formEmail.text
        val password = binding.formPassword.text


        binding.btnRegister.setOnClickListener {
            val intentRegister = Intent(this@LoginFormActivity, RegisterFormActivity::class.java)

            intentRegister.putExtra(RegisterFormActivity.INTENT_EMAIL, "$email")
            intentRegister.putExtra(RegisterFormActivity.INTENT_PASSWORD, "$password")
            startActivity(intentRegister)
        }
    }
}