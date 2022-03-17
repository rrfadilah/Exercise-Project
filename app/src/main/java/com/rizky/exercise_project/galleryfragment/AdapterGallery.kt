package com.rizky.exercise_project.galleryfragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class AdapterGallery(val fragmentManager: FragmentManager, val listFramgent: List<Fragment>):
    FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return listFramgent.size
    }

    override fun getItem(position: Int): Fragment {
        return listFramgent[position]
    }
}