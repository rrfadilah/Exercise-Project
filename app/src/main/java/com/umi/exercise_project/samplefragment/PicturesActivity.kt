package com.umi.exercise_project.samplefragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umi.exercise_project.R

class PicturesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pictures)

        val picturesFragment = PicturesFragment ()
        val fragmentManager= supportFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()

        fragmentTransition.add(R.id.flBottom, picturesFragment)
        fragmentTransition.commit()
    }
}