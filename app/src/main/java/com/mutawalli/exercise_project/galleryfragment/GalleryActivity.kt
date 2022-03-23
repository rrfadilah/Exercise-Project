package com.mutawalli.exercise_project.galleryfragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mutawalli.exercise_project.databinding.ActivityGalleryBinding
import com.mutawalli.exercise_project.samplefragment.PicturesFragment

class GalleryActivity : AppCompatActivity() {
    lateinit var binding: ActivityGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listGallery = listOf(
            GalleryFragment(),
            PicturesFragment(),
            GalleryFragment()
        )

        val adapter = AdapterGallery(supportFragmentManager, listGallery)
        binding.vpGallery.adapter = adapter

    }
}