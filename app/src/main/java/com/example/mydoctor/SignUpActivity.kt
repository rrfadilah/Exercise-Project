package com.example.mydoctor

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import com.example.mydoctor.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    companion object {
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

        if (bundle != null) {
            binding.inputEmail.setText(email)
            binding.inputPassword.setText(password)
        }

        val pref = this.getSharedPreferences(Constant.Register.PREF_REGISTER, MODE_PRIVATE)
        binding.btnSignUp.setOnClickListener {
            sharedPreferences(pref)
        }
    }

    private fun emailValidated(target: CharSequence): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    fun sharedPreferences(pref: SharedPreferences) {
        val fullName = binding.inputFullName.text.toString()
        val pekerjaan = binding.inputPekerjaan.text.toString()
        val email = binding.inputEmail.text.toString()
        val password = binding.inputPassword.text.toString()

        if (email.isEmpty() || password.isEmpty() || fullName.isEmpty() || pekerjaan.isEmpty()) {
            Toast.makeText(
                binding.btnSignUp.context,
                "Semua Field tidak boleh kosong",
                Toast.LENGTH_SHORT
            )
                .show()
        } else if (!emailValidated(email.trim())) {
            Toast.makeText(binding.btnSignUp.context, "Email tidak valid", Toast.LENGTH_SHORT)
                .show()
        } else if (password.length < 8) {
            Toast.makeText(
                binding.btnSignUp.context,
                "Password kurang dari 8 karakter",
                Toast.LENGTH_SHORT
            )
                .show()
        } else if (!password.matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))) {
            Toast.makeText(
                binding.btnSignUp.context,
                "Password harus mengandung upper case dan lowercase",
                Toast.LENGTH_SHORT
            )
                .show()
        } else {
            pref.edit {
                putString(Constant.Register.KEY.FULLNAME, fullName)
                putString(Constant.Register.KEY.PEKERJAAN, pekerjaan)
                putString(Constant.Register.KEY.EMAIL, email)
                putString(Constant.Register.KEY.PASSWORD, password)
                apply()
            }
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}