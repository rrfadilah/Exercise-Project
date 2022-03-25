package com.rizky.exercise_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.rizky.exercise_project.Doctor.DoctorFragment
import com.rizky.exercise_project.databinding.ActivityMainBinding
import com.rizky.exercise_project.hospitals.HospitalsFragment
import com.rizky.exercise_project.message.MessageFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentDoctor = DoctorFragment()
        val fragmentHospitals = HospitalsFragment()
        val fragmentMessages = MessageFragment()

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