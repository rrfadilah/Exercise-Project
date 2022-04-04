package com.sulthoni.exercise_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.sulthoni.exercise_project.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    companion object{
        const val INTENT_EMAIL = "inputEmail"
        const val INTENT_PASSWORD = "inputPassword"
    }

    lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val inputEmail = bundle?.getString(INTENT_EMAIL)
        val inputPassword = bundle?.getString(INTENT_PASSWORD)

        if (bundle != null){
            binding.editText5.setText(inputEmail)
            binding.editText6.setText(inputPassword)
        }

    }
}