package com.rizkirafiif.exercise_project.galleryfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewParent
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.rizkirafiif.exercise_project.R
import com.rizkirafiif.exercise_project.databinding.ActivityGalleryBinding
import com.rizkirafiif.exercise_project.samplefragment.PicturesFragment

class GalleryActivity : AppCompatActivity() {
    lateinit var binding:ActivityGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listGallery = listOf<Fragment>(
            GalleryFragment(),
            GalleryFragment(),
            GalleryFragment()
        )

        val adapter = AdapterGallery(supportFragmentManager, listGallery)
        binding.vpGallery.adapter = adapter
    }
}