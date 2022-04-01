package com.example.mydoctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mydoctor.databinding.ActivityGetStartedBinding

class GetStartedActivity : AppCompatActivity() {

    lateinit var binding: ActivityGetStartedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_get_started)
        binding = ActivityGetStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getStarted()
        signIn()
    }

    fun getStarted() {
        binding.btnGetStarted.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    fun signIn() {
        binding.btnSignIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}