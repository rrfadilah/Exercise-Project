package com.tegarpenemuan.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tegarpenemuan.myapplication.databinding.ActivitySignBinding

class SignActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvCreateNewAccount.setOnClickListener {
            startActivity(Intent(this,SignupActivity::class.java))
        }
    }
}