package com.rizky.exercise_project.facebook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rizky.exercise_project.R
import com.rizky.exercise_project.facebook.ui.group_facebook.GroupFacebookFragment
import com.rizky.exercise_project.facebook.ui.home_facebook.HomeFragment
import com.rizky.exercise_project.facebook.ui.live_facebook.LiveFacebookFragment
import com.rizky.exercise_project.facebook.ui.notifications_facebook.NotificationsFacebookFragment
import com.rizky.exercise_project.facebook.ui.person_facebook.PersonFacebookFragment


class Facebook : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facebook)

        val fragmentHomeFacebook = HomeFragment()
        val fragmentGroupFacebook = GroupFacebookFragment()
        val fragmentLiveFacebook = LiveFacebookFragment()
        val fragmentPersonFacebook = PersonFacebookFragment()
        val fragmentNotificationFacebook = NotificationsFacebookFragment()
        val navViewFacebook = findViewById<BottomNavigationView>(R.id.nav_view_facebook)

        setCurrentFragment(fragmentHomeFacebook)

        navViewFacebook.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_facebook -> setCurrentFragment(fragmentHomeFacebook)
                R.id.group_facebook -> setCurrentFragment(fragmentGroupFacebook)
                R.id.live_facebook -> setCurrentFragment(fragmentLiveFacebook)
                R.id.person_facebook -> setCurrentFragment(fragmentPersonFacebook)
                R.id.notification_facebook -> setCurrentFragment(fragmentNotificationFacebook)
            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flLinkedIn_facebook, fragment)
            commit()
        }
}
