package com.rizkirafiif.exercise_project.samplefragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.rizkirafiif.exercise_project.R
import com.rizkirafiif.exercise_project.galleryfragment.GalleryActivity

class PicturesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pictures)

        val picturesFragment = PicturesFragment()

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.flBottom, picturesFragment)
        fragmentTransaction.commit()

        // for hiding the action bar
        supportActionBar?.hide()

        // just to auto slide to sign in activity
        Handler().postDelayed({
            val intent = Intent(this, GalleryActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}