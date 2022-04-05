package com.example.exercise_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val btnContinue = findViewById<Button>(R.id.btn_continue)
        val registerPreferences =
            this.getSharedPreferences(Constant.Register.PREF_REGISTER_NAME, MODE_PRIVATE)


        btnContinue.setOnClickListener {
            val email = findViewById<EditText>(R.id.et_register_email_address).text.toString()
            val password = findViewById<EditText>(R.id.et_register_password).text.toString()
            val nama = findViewById<EditText>(R.id.et_register_full_name).text.toString()
            val pekerjaan = findViewById<EditText>(R.id.et_register_pekerjaan).text.toString()


            if (email.isEmpty() || password.isEmpty() || nama.isEmpty() || pekerjaan.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else if (!password.matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))) {
                Toast.makeText(this, R.string.password_lower_upper_case, Toast.LENGTH_SHORT).show()
            } else if (password.length < 8) {
                Toast.makeText(this, R.string.password_character, Toast.LENGTH_SHORT).show()
            } else if (!email
                    .matches(Regex("^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$"))
            ) {
                Toast.makeText(this, R.string.email_salah, Toast.LENGTH_SHORT).show()
            }
            else if (registerPreferences.getString(Constant.Register.KEY.NAMA, "") == email
                || registerPreferences.getString(Constant.Register.KEY.PASSWORD, "") == password
                || registerPreferences.getString(Constant.Register.KEY.EMAIL, "") == nama
                || registerPreferences.getString(Constant.Register.KEY.PEKERJAAN, "") == pekerjaan
            ) {
                Toast.makeText(this, "Data sudah ada di sharedpreferences", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val editor = registerPreferences.edit()
                editor.putString(Constant.Register.KEY.EMAIL, email)
                editor.putString(Constant.Register.KEY.PASSWORD, password)
                editor.putString(Constant.Register.KEY.NAMA, nama)
                editor.putString(Constant.Register.KEY.PEKERJAAN, pekerjaan)
                editor.apply()

                val intent = Intent(this, SignActivity::class.java)
                startActivity(intent)
            }


        }
    }
}