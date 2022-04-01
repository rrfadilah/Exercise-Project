package com.example.mydoctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.addCallback
import com.example.mydoctor.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    companion object{
        const val INTENT_EMAIL = "input_email"
        const val INTENT_PASSWORD = "input_password"
    }

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val email = bundle?.getString(INTENT_EMAIL)
        val password = bundle?.getString(INTENT_PASSWORD)

        if (bundle != null){
            binding.inputEmail.setText(email)
            binding.inputPassword.setText(password)
        }

        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}