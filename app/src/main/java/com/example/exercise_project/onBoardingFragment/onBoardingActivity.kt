package com.example.exercise_project.onBoardingFragment

import Transform
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.inflate
import android.widget.ImageButton
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.example.exercise_project.GetStartedActivity
import com.example.exercise_project.R
import com.example.exercise_project.databinding.ActivityGalleryBinding.inflate
import com.example.exercise_project.databinding.ActivityMainBinding.inflate
import com.example.exercise_project.databinding.ActivityOnBoardingBinding
import com.example.exercise_project.databinding.ActivityPicturesBinding.inflate
import com.example.exercise_project.databinding.FragmentOnBoardingFourthBinding
import com.example.exercise_project.galleryfragment.GalleryFragment
import com.example.exercise_project.samplefragment.PicturesFragment
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class onBoardingActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listOnBoarding = listOf(
            onBoardingFragment(),
            onBoardingFragmentSecond(),
            onBoardingFragmentThird(),
            onBoardingFragmentFourth()
        )

        val adapter = onBoardingAdapter(supportFragmentManager, listOnBoarding)
        binding.vpOnBoarding.adapter = adapter
        binding.tlIndicator.setViewPager(binding.vpOnBoarding)
        binding.vpOnBoarding.setPageTransformer(true,Transform())
    }
}
