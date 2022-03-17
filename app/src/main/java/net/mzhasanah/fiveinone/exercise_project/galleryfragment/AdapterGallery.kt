package net.mzhasanah.fiveinone.exercise_project.galleryfragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class AdapterGallery(
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