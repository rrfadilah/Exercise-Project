package net.mzhasanah.fiveinone.exercise_project.samplefragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.mzhasanah.fiveinone.exercise_project.R

class PicturesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pictures)

        val picturesFragment = PicturesFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.flBottom, picturesFragment)
        fragmentTransaction.commit()
    }
}