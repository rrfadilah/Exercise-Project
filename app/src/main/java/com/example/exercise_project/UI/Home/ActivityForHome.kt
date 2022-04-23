package com.example.exercise_project.UI.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.exercise_project.UI.Home.Doctor.FragmentDoctor
import com.example.exercise_project.UI.Home.Hospitals.FragmentHospitals
import com.example.exercise_project.UI.Home.Messages.FragmentMessages
import com.example.exercise_project.R
import com.example.exercise_project.databinding.ActivityForHomeBinding

class ActivityForHome : AppCompatActivity() {
    lateinit var binding: ActivityForHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_home)
        binding = ActivityForHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentDoctor = FragmentDoctor()
        val fragmentHospitals = FragmentHospitals()
        val fragmentMessages = FragmentMessages()

        setCurrentFragment(fragmentDoctor)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navbar_Doctor -> setCurrentFragment(fragmentDoctor)
                R.id.navbar_Messages -> setCurrentFragment(fragmentMessages)
                R.id.navbar_Hospital -> setCurrentFragment(fragmentHospitals)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.flFragment,fragment)
        commit()
    }
}