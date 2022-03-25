package com.mhus.exercise_project.samplefragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mhus.exercise_project.R

class PictureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)

        val pictureFragment = PictureFragment()

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.flBottom,pictureFragment)
        fragmentTransaction.commit()
    }
}