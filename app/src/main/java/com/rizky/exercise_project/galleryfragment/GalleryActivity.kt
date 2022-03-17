package com.rizky.exercise_project.galleryfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.rizky.exercise_project.R
import com.rizky.exercise_project.databinding.ActivityGalleryBinding
import com.rizky.exercise_project.samplefragment.PicturesFragment

class GalleryActivity : AppCompatActivity() {
    lateinit var binding: ActivityGalleryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val listGallery = listOf<Fragment>(
            GalleryFragment(),
            PicturesFragment(),
            GalleryFragment(),
        )

        binding.vpGallery
        val adapter = AdapterGallery(supportFragmentManager, listGallery)
        binding.vpGallery.adapter = adapter

    }
}