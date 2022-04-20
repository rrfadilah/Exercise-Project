package com.example.exercise_project.UI.onBoarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class onBoardingAdapter(fragmentManager: FragmentManager, val listFragment: List<Fragment>):
    FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return listFragment.size
    }

    override fun getItem(position: Int): Fragment {
        return listFragment[position]
    }
}