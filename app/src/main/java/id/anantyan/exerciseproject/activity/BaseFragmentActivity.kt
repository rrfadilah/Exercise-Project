package id.anantyan.exerciseproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.get
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import id.anantyan.exerciseproject.R
import id.anantyan.exerciseproject.adapter.MainViewPagerAdapter
import id.anantyan.exerciseproject.databinding.ActivityBaseFragmentBinding
import id.anantyan.exerciseproject.fragment.DoctorFragment
import id.anantyan.exerciseproject.fragment.HospitalFragment
import id.anantyan.exerciseproject.fragment.MessagesFragment
import id.anantyan.exerciseproject.model.DataDummy
import id.anantyan.exerciseproject.utils.Constant
import id.anantyan.exerciseproject.utils.Constant.PASSING_TO_BASE_FRAGMENT
import id.anantyan.exerciseproject.utils.viewbinding.viewBinding

class BaseFragmentActivity : AppCompatActivity() {

    private val binding: ActivityBaseFragmentBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_fragment)
        setSupportActionBar(binding.include.toolbar)
        supportActionBar?.setTitle(R.string.txt_title)
        onBinding()
    }

    private fun onBinding() {
        val item = intent.extras?.getParcelable<DataDummy>(PASSING_TO_BASE_FRAGMENT)
        val itemFragment = listOf(
            DoctorFragment.newInstance(item),
            MessagesFragment(),
            HospitalFragment()
        )
        val sectionViewPager = MainViewPagerAdapter(
            supportFragmentManager,
            lifecycle,
            itemFragment
        )
        binding.viewPager.adapter = sectionViewPager
        binding.viewPager.isUserInputEnabled = false // untuk disable swipe layout
        binding.bottomNavigation.setOnItemSelectedListener(onNavigationListener)
    }

    private fun onSetViewPager(it: Int) {
        binding.viewPager.setCurrentItem(it, false) // untuk disable animate slide
    }

    private fun onSetBottomNavigation(resId: Int) {
        binding.bottomNavigation.menu.findItem(resId).isChecked = true
    }

    override fun onBackPressed() {
        if (binding.viewPager.currentItem > 0) {
            onSetViewPager(0)
            onSetBottomNavigation(R.id.navigation_doctor)
        } else {
            super.onBackPressed()
        }
    }

    private val onNavigationListener = NavigationBarView.OnItemSelectedListener {
        return@OnItemSelectedListener when(it.itemId) {
            R.id.navigation_doctor -> {
                onSetViewPager(0)
                true
            }
            R.id.navigation_chat -> {
                onSetViewPager(1)
                true
            }
            R.id.navigation_map -> {
                onSetViewPager(2)
                true
            }
            else -> false
        }
    }
}