package com.rizky.exercise_project.Facebook

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.rizky.exercise_project.R
import com.rizky.exercise_project.databinding.ActivityFacebookBinding

class Facebook : AppCompatActivity() {

    private lateinit var binding: ActivityFacebookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFacebookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_facebook)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home_facebook, R.id.navigation_group_facebook, R.id.navigation_person_facebook, R.id.navigation_notification_facebook, R.id.navigation_live_facebook, R.id.navigation_menu_facebook
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}