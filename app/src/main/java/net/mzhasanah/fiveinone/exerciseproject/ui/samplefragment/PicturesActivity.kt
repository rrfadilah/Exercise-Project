package net.mzhasanah.fiveinone.exerciseproject.ui.samplefragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.mzhasanah.fiveinone.exerciseproject.R

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