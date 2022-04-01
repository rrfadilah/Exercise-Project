package com.rizky.exercise_project.LinkedIn

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rizky.exercise_project.Doctor.DoctorFragment
import com.rizky.exercise_project.R
import com.rizky.exercise_project.databinding.ActivityLinkedinBinding
import com.rizky.exercise_project.databinding.ActivityMainBinding
import com.rizky.exercise_project.hospitals.HospitalsFragment

class LinkedInActivity: AppCompatActivity() {
    lateinit var binding: ActivityLinkedinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linkedin)
        binding = ActivityLinkedinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentHome = HomeLinkedInFragment()

        setCurrentFragment(fragmentHome)

        binding.navigationbottomLinkedIn.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navbar_home -> setCurrentFragment(fragmentHome)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.flLinkedIn,fragment)
        commit()
    }
}