package com.rizkirafiif.exercise_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class OnBoardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        // for hiding the action bar
        supportActionBar?.hide()

        // just to auto slide to sign in activity
        Handler().postDelayed({
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}