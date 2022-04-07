package com.mhus.exercise_project

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class AdapterOnBoarding(
    fragmentManager: FragmentManager,
    val listfragment: List<Fragment>
    ) :
    FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return listfragment.size
    }

    override fun getItem(position: Int): Fragment {
        return listfragment[position]
    }
}