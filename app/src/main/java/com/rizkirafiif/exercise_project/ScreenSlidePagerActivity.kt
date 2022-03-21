package com.rizkirafiif.exercise_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager

class ScreenSlidePagerActivity : AppCompatActivity() {
    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private lateinit var mPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_slide_pager)
        val listFramentGambar = listOf<Fragment>(
            OnBoardingActivity(),
            ViewSatuFragment(),
            ViewDuaFragment(),
            ViewTigaFragment(),
            ViewEmpatFragment()
        )

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = findViewById(R.id.viewPagerMC)

        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager, listFramentGambar)
        mPager.adapter = pagerAdapter
    }

    // fungsi tombol back
    override fun onBackPressed() {
        if (mPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            mPager.currentItem = mPager.currentItem - 1
        }
    }


    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private inner class ScreenSlidePagerAdapter(fm: FragmentManager, val listFragment:List<Fragment>)
        : FragmentStatePagerAdapter(fm) {


        override fun getCount(): Int{
            return listFragment.size
        }

        override fun getItem(position: Int): Fragment {
            return listFragment[position]
        }
    }
}