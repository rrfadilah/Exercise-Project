package com.tegarpenemuan.myapplication.galleryfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.tegarpenemuan.myapplication.databinding.ActivityGalleryBinding

class GalleryActivity : AppCompatActivity() {
    lateinit var binding: ActivityGalleryBinding

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