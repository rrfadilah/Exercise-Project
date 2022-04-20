package com.example.exercise_project.Others.galleryfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exercise_project.R
import com.example.exercise_project.databinding.ActivityGalleryBinding
import com.example.exercise_project.Others.samplefragment.PicturesFragment

class GalleryActivity : AppCompatActivity() {
    lateinit var binding: ActivityGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
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