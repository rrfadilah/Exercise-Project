package com.example.mydoctor

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mydoctor.databinding.ActivityNavigationBinding
import com.example.mydoctor.ui.doctor.DoctorFragment
import com.example.mydoctor.ui.hospitals.HospitalsFragment
import com.example.mydoctor.ui.messages.MessagesFragment

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//        val navView: BottomNavigationView = binding.navView
//
//        val navController = findNavController(R.id.nav_host_fragment_activity_navigation)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_doctor, R.id.navigation_messages, R.id.navigation_hospitals
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

        var doctorFragment = DoctorFragment()
        var messagesFragment = MessagesFragment()
        var hospitalsFragment = HospitalsFragment()

        setCurrentFragment(doctorFragment)

        binding.navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_doctor -> setCurrentFragment(doctorFragment)
                R.id.navigation_messages -> setCurrentFragment(messagesFragment)
                R.id.navigation_hospitals -> setCurrentFragment(hospitalsFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment_activity_navigation, fragment)
            commit()
        }
    }
}