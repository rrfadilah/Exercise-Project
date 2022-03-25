package com.mhus.exercise_project.galeryfragment

import androidx.fragment.app.*

class AdapterGallery(
    fragmentManager: FragmentManager,
    val listFragment: List<Fragment>
) :
    FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return listFragment.size
    }

    override fun getItem(position: Int): Fragment {
        return listFragment[position]
    }
}