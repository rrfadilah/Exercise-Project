package com.rizky.exercise_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val pref = this.getSharedPreferences(Constant.Preferences.PREF_NAME, MODE_PRIVATE)

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