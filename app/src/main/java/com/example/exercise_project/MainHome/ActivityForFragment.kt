package com.example.exercise_project.MainHome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.exercise_project.R
import com.example.exercise_project.databinding.ActivityForFragmentBinding

class ActivityForFragment : AppCompatActivity() {
    lateinit var binding: ActivityForFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_fragment)
        binding = ActivityForFragmentBinding.inflate(layoutInflater)
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