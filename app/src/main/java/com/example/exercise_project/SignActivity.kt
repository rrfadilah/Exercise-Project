package com.example.exercise_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class SignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)


        val btncreatenewaccount = findViewById<TextView>(R.id.tv_create_new_account)
        btncreatenewaccount.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        val mail = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")


        val edtmail = findViewById<EditText>(R.id.et_email)
        val edtpassword = findViewById<EditText>(R.id.et_password)


        edtmail.setText(mail)
        edtpassword.setText(password)


    }
}