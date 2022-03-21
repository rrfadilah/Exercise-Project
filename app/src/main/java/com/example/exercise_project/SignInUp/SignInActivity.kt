package com.example.exercise_project.SignInUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exercise_project.R
import com.example.exercise_project.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    companion object {
        const val EMAIL = "EMAIL"
        const val PASS = "PASS"
    }
    lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvGoToSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            val email = binding.etEmailSignIn.text.toString()
            val pass = binding.etPassSignIn.text.toString()

            intent.putExtra(SignInActivity.EMAIL, email)
            intent.putExtra(SignInActivity.PASS, pass)
            startActivity(intent)
        }

    }
}