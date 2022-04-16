package com.tegarpenemuan.myapplication.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tegarpenemuan.myapplication.Constant
import com.tegarpenemuan.myapplication.databinding.ActivitySignupBinding
import com.tegarpenemuan.myapplication.ui.signin.SignInActivity
import com.tegarpenemuan.myapplication.utils.showCustomToast

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val registerPreferences =
            this.getSharedPreferences(Constant.Register.PREF_REGISTER_NAME, MODE_PRIVATE)

        binding.ibBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnContinue.setOnClickListener {
            if (binding.etRegisterFullName.text.isEmpty()) {
                Toast(this).showCustomToast("Fullname Tidak Boleh Kosong", this)
            } else if (binding.etRegisterPekerjaan.text.isEmpty()) {
                Toast(this).showCustomToast("Pekerjaan Tidak Boleh Kosong", this)
            } else if (binding.etRegisterEmailAddress.text.isEmpty()) {
                Toast(this).showCustomToast("Email Tidak Boleh Kosong", this)
            } else if (binding.etRegisterPassword.text.isEmpty()) {
                Toast(this).showCustomToast("Pekerjaan Tidak Boleh Kosong", this)
            } else if (!binding.etRegisterEmailAddress.text.matches(Regex("^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$"))) {
                Toast(this).showCustomToast("Email Tidak Valid", this)
            } else if (binding.etRegisterPassword.text.length < 8) {
                Toast(this).showCustomToast("Password harus lebih dari 8 karakter", this)
            } else if (!binding.etRegisterPassword.text.matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))) {
                Toast(this).showCustomToast("Password harus mengandung upper case dan lowercase", this)
            } else if (registerPreferences.getString(
                    Constant.Register.KEY.NAMA, ""
                ) == binding.etRegisterFullName.text.toString()
                || registerPreferences.getString(
                    Constant.Register.KEY.PASSWORD,
                    ""
                ) == binding.etRegisterPassword.text.toString()
                || registerPreferences.getString(
                    Constant.Register.KEY.EMAIL,
                    ""
                ) == binding.etRegisterEmailAddress.text.toString()
                || registerPreferences.getString(
                    Constant.Register.KEY.PEKERJAAN,
                    ""
                ) == binding.etRegisterPassword.text.toString()
            ) {
                Toast(this).showCustomToast("Data Sudah Ada Di Sharepreferences", this)
            } else {
                val editor = registerPreferences.edit()
                editor.putString(
                    Constant.Register.KEY.EMAIL,
                    binding.etRegisterEmailAddress.text.toString()
                )
                editor.putString(
                    Constant.Register.KEY.PASSWORD,
                    binding.etRegisterPassword.text.toString()
                )
                editor.putString(Constant.Register.KEY.NAMA, binding.etRegisterFullName.text.toString())
                editor.putString(
                    Constant.Register.KEY.PEKERJAAN,
                    binding.etRegisterPekerjaan.text.toString()
                )
                editor.putBoolean(Constant.Register.KEY.LOGIN, false)
                editor.apply()

                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
            }
        }
    }
}