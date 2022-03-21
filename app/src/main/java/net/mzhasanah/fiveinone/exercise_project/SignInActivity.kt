package net.mzhasanah.fiveinone.exercise_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
    }

    fun ClickCreateNewAccount(V: View?) {
        val etDataEmail = findViewById<View>(R.id.etEmail) as EditText
        val etDataPassword = findViewById<View>(R.id.etPassword) as EditText
        var tvCreate = findViewById<View>(R.id.tvCreateAccount) as TextView
        val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
        intent.putExtra("dataEmail", etDataEmail.getText().toString())
        intent.putExtra("dataPassword", etDataPassword.getText().toString())
        startActivity(intent)
    }
}