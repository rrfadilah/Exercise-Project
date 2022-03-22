package id.anantyan.exerciseproject.activity

import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationBarView
import id.anantyan.exerciseproject.R
import id.anantyan.exerciseproject.adapter.MainViewPagerAdapter
import id.anantyan.exerciseproject.databinding.ActivityBaseFragmentBinding
import id.anantyan.exerciseproject.fragment.DoctorFragment
import id.anantyan.exerciseproject.fragment.HospitalFragment
import id.anantyan.exerciseproject.fragment.MessagesFragment
import id.anantyan.exerciseproject.fragment.SharedViewModel
import id.anantyan.exerciseproject.model.Messages
import id.anantyan.utils.Constant.PASSING_TO_MESSAGES_ACTIVITY
import id.anantyan.utils.viewbinding.viewBinding

class BaseFragmentActivity : AppCompatActivity() {

    private lateinit var sectionViewPager: MainViewPagerAdapter
    private val viewModel: SharedViewModel by viewModels()
    private val binding: ActivityBaseFragmentBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_fragment)
        setSupportActionBar(binding.include.toolbar)
        supportActionBar?.setTitle(R.string.txt_title)
        onBinding()
    }

    private fun onBinding() {
        val itemFragment = listOf(
            DoctorFragment(),
            MessagesFragment(),
            HospitalFragment()
        )
        sectionViewPager = MainViewPagerAdapter(
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
        if (binding.viewPager.currentItem > 0 && binding.viewPager.currentItem <= sectionViewPager.itemCount - 1) {
            onSetViewPager(0)
            onSetBottomNavigation(R.id.navigation_doctor)
        } else {
            super.onBackPressed()
        }
    }

    private val onNavigationListener = NavigationBarView.OnItemSelectedListener {
        return@OnItemSelectedListener when (it.itemId) {
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

    val onResultActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            val items = it.data?.getParcelableExtra<Messages>(PASSING_TO_MESSAGES_ACTIVITY)
            items?.let { it1 ->
                viewModel.setMessages(it1)
            }
        }
    }
}