package com.umi.exercise_project.galleryfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.umi.exercise_project.R
import com.umi.exercise_project.databinding.ActivityGalleryBinding
import com.umi.exercise_project.samplefragment.PicturesFragment

class GalleryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(binding.root)

        val listGallery = listOf<Fragment>(
            GalleryFragment (),
            PicturesFragment (),
            GalleryFragment ()

        )

        // Perbedaannya kalau 1 perlu membuat var. untuk menampung id kalau 2 menggunakan binding.
        // binding = tidak perlu membuat var. unt membuat id

        //menggunakan variabel biasa (1. perlu 3 baris)
        //val vpGallery: ViewPager = findViewById(R.id.vpGallery) //1
        //val adapter = AdapterGallery(supportFragmentManager) //1
        // vpGallery.adapter = adapter //1

        //ini menggunakan binding (2)
        val adapter = AdapterGallery(supportFragmentManager, listGallery) //2
        binding.vpGallery.adapter = adapter //2




    }

}