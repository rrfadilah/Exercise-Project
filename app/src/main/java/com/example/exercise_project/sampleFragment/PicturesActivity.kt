package com.example.exercise_project.sampleFragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exercise_project.R

class PicturesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pictures)

        val picturesFragment = PicturesFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.frameLayoutPictures, picturesFragment)
        fragmentTransaction.commit()
    }
}