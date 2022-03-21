package com.rizkirafiif.exercise_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class OnBoardingActivity : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_on_boarding)

//        // for hiding the action bar
//        supportActionBar?.hide()
//
//        // just to auto slide to sign in activity
//        Handler().postDelayed({
//            val intent = Intent(this, ScreenSlidePagerActivity::class.java)
//            startActivity(intent)
//            finish()
//        }, 3000)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_on_boarding, container, false)
    }
}