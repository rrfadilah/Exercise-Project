package net.mzhasanah.fiveinone.exercise_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val etEmailData = findViewById<View>(R.id.etEmailAddress) as EditText
        val etPasswordData = findViewById<View>(R.id.etPassword2) as EditText
        etEmailData.setText(intent.getStringExtra("dataEmail"))
        etPasswordData.setText(intent.getStringExtra("dataPassword"))
    }

    fun ClickArrowBack(V: View?) {
        val intent = Intent(this, OnBoardingActivity::class.java)
        startActivity(intent)
        finish()
    }
}