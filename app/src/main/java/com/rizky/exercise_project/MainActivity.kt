package com.rizky.exercise_project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.rizky.exercise_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        val appBar = AppBarConfiguration(
            setOf(
                R.id.navigation_doctor,
                R.id.navigation_messages,
                R.id.navigation_hospital
            )
        )

        binding.toolbar.setupWithNavController(navHost.navController, appBar)
        binding.bottomNavigationView.setupWithNavController(navHost.navController)
    }
}