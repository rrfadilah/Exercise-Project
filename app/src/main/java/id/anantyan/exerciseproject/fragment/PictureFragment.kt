package id.anantyan.exerciseproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import id.anantyan.exerciseproject.R
import id.anantyan.exerciseproject.activity.basefragment.BaseFragmentActivity
import id.anantyan.exerciseproject.activity.basefragment.BaseFragmentViewPagerAdapter
import id.anantyan.exerciseproject.databinding.FragmentPictureBinding
import id.anantyan.exerciseproject.utils.viewbinding.viewBinding

class PictureFragment : Fragment() {

    private val binding: FragmentPictureBinding by viewBinding()
    private val onBinding: () -> Unit = {
        binding.imgView.setOnClickListener {
            (activity as BaseFragmentActivity).onSetViewPager(1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_picture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBinding()
    }
}