package com.rizky.exercise_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_form)

        var register: Button = findViewById(R.id.btnSignIn)
        var emailAddress: EditText = findViewById(R.id.formEmail)

        register.setOnClickListener {
            val intenRegister = Intent(this@LoginFormActivity, LoginFormActivity::class.java)
            val bundle = Bundle()

            bundle.putString("key", "value")
            intenRegister.putExtra("key", "value")
            startActivity(intenRegister)
        }
    }
}