package com.rizky.exercise_project.facebook

import ReelsFacebookAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rizky.exercise_project.R
import com.rizky.exercise_project.databinding.ActivityFacebookBinding
import com.rizky.exercise_project.facebook.ui.group_facebook.GroupFacebookFragment
import com.rizky.exercise_project.facebook.ui.home_facebook.HomeFragment
import com.rizky.exercise_project.facebook.ui.live_facebook.LiveFacebookFragment
import com.rizky.exercise_project.facebook.ui.notifications_facebook.NotificationsFacebookFragment
import com.rizky.exercise_project.facebook.ui.person_facebook.PersonFacebookFragment


class Facebook : AppCompatActivity() {
    lateinit var binding: ActivityFacebookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facebook)
        binding = ActivityFacebookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navViewFacebook.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home_facebook -> setCurrentFragment(HomeFragment())
                R.id.navigation_group_facebook -> setCurrentFragment(GroupFacebookFragment())
                R.id.navigation_live_facebook -> setCurrentFragment(LiveFacebookFragment())
                R.id.navigation_person_facebook -> setCurrentFragment(PersonFacebookFragment())
                R.id.navigation_notification_facebook -> setCurrentFragment(NotificationsFacebookFragment())

            }
            true
        }
        setCurrentFragment(HomeFragment())
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_activity_facebook, fragment)
            .commit()
    }
}