package com.example.mydoctor.ui.signup

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.core.content.edit
import com.example.mydoctor.Constant
import com.example.mydoctor.databinding.ActivitySignUpBinding
import com.example.mydoctor.ui.signin.SignInActivity

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

        val pref = this.getSharedPreferences(Constant.Preferences.PREF_NAME, MODE_PRIVATE)
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
                "Fields tidak boleh kosong",
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
        } else if (fullName == pref.getString(Constant.Preferences.KEY.FULLNAME, "") ||
            pekerjaan == pref.getString(Constant.Preferences.KEY.PEKERJAAN, "") ||
            email == pref.getString(Constant.Preferences.KEY.EMAIL, "") ||
            password == pref.getString(Constant.Preferences.KEY.PASSWORD, "")
        ) {
            Toast.makeText(this, "Akun Sudah Terdaftar", Toast.LENGTH_SHORT)
                .show()
        } else {
            pref.edit {
                putString(Constant.Preferences.KEY.FULLNAME, fullName)
                putString(Constant.Preferences.KEY.PEKERJAAN, pekerjaan)
                putString(Constant.Preferences.KEY.EMAIL, email)
                putString(Constant.Preferences.KEY.PASSWORD, password)
                apply()
            }
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}