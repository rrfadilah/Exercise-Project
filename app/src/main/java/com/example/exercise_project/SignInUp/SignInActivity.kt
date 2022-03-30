package com.example.exercise_project.SignInUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.exercise_project.R
import com.example.exercise_project.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvGoToSignUp.setOnClickListener {
            val email = binding.etEmailSignIn.text.toString()
            val pass = binding.etPassSignIn.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(this, "Kolom email masih kosong", Toast.LENGTH_SHORT).show()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Email tidak valid", Toast.LENGTH_SHORT).show()
            } else if (pass.isEmpty()) {
                Toast.makeText(this, "Kolom password masih kosong", Toast.LENGTH_SHORT).show()
            } else if (pass.length < 8) {
                Toast.makeText(this, "Minimum 8 Characters", Toast.LENGTH_SHORT).show()
            } else if (!pass.matches(".*[A-Z].*".toRegex())) {
                Toast.makeText(this, "Password harus mengandung 1 Upper-case", Toast.LENGTH_SHORT)
                    .show()
            } else if (!pass.matches(".*[a-z].*".toRegex())) {
                Toast.makeText(this, "Password harus mengandung 1 Lower-case", Toast.LENGTH_SHORT)
                    .show()
            }
            return@setOnClickListener
        }
        openSigninPage()
    }

    fun openSignUpPage(){
        Toast.makeText(this, "Anda membuka halaman sign up", Toast.LENGTH_LONG).show()
    }
    fun openSigninPage(){
        Toast.makeText(this, "Selamat datang di halaman sign in", Toast.LENGTH_LONG).show()
    }

}