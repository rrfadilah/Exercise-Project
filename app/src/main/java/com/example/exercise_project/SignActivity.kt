package com.example.exercise_project

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class SignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        val mail = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")

        val edtmail = findViewById<EditText>(R.id.et_email)
        val edtpassword = findViewById<EditText>(R.id.et_password)

        edtmail.setText(mail)
        edtpassword.setText(password)

        openPage()
        val btnlogin = findViewById<TextView>(R.id.btn_sign_in)
        btnlogin.setOnClickListener {
            if (edtmail.text.toString().isEmpty()) {
                Toast.makeText(this, R.string.email_kosong, Toast.LENGTH_SHORT).show()
            } else if (edtpassword.text.toString().isEmpty()) {
                Toast.makeText(this, R.string.password_kosong, Toast.LENGTH_SHORT).show()
            } else if (edtpassword.text.toString().length < 8) {
                Toast.makeText(this, R.string.password_character, Toast.LENGTH_SHORT).show()
            } else if (!edtpassword.text.toString().matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))) {
                Toast.makeText(this, R.string.password_lower_upper_case, Toast.LENGTH_SHORT).show()
            } else if (!edtmail.text.toString()
                    .matches(Regex("^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$"))
            ) {
                Toast.makeText(this, R.string.email_salah, Toast.LENGTH_SHORT).show()
            } else {
                openPage()
            }
        }
        val btnCreateNewAccount = findViewById<TextView>(R.id.tv_create_new_account)
        btnCreateNewAccount.setOnClickListener {
            Toast.makeText(this, "Tombol create new account di pencet", Toast.LENGTH_SHORT).show()
        }
        val btnForgotPassword = findViewById<TextView>(R.id.tv_forgot_password)
        btnForgotPassword.setOnClickListener {
            Toast.makeText(this, "Tombol forgot password di pencet", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openPage() {

        Toast.makeText(this, "Membuka halaman Sign In", Toast.LENGTH_LONG).show()
        val snackbar = Snackbar.make(
            findViewById(R.id.cl_root),
            "Membuka halaman Sign In",
            Snackbar.LENGTH_LONG
        )
        snackbar.show()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "Tombol back di pencet", Toast.LENGTH_SHORT).show()
    }
}