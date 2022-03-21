package com.rizkirafiif.exercise_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.rizkirafiif.exercise_project.samplefragment.PicturesActivity

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // for hiding the action bar
        supportActionBar?.hide()

        // just to auto slide to sign in activity
        Handler().postDelayed({
            val intent = Intent(this, PicturesActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}