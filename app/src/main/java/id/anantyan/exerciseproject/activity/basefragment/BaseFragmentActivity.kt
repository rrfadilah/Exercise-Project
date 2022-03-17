package id.anantyan.exerciseproject.activity.basefragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import id.anantyan.exerciseproject.R
import id.anantyan.exerciseproject.databinding.ActivityBaseFragmentBinding
import id.anantyan.exerciseproject.fragment.PictureFragment
import id.anantyan.exerciseproject.fragment.VideoFragment
import id.anantyan.exerciseproject.utils.viewbinding.viewBinding

class BaseFragmentActivity : AppCompatActivity() {

    private val binding: ActivityBaseFragmentBinding by viewBinding()
    private val onBinding: () -> Unit = {
        val itemFragment = listOf(
            PictureFragment(),
            VideoFragment()
        )
        val sectionViewPager = BaseFragmentViewPagerAdapter(
            supportFragmentManager,
            lifecycle,
            itemFragment
        )
        binding.viewPager.adapter = sectionViewPager
        binding.viewPager.isUserInputEnabled = false // untuk disable swipe layout
    }
    val onSetViewPager: (Int) -> Unit = {
        binding.viewPager.setCurrentItem(it, false) // untuk disable animate slide
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_fragment)
        onBinding()
    }
}