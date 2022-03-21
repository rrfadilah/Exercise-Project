package com.example.exercise_project.onBoardingFragment

import android.content.Intent
import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import butterknife.ButterKnife
import com.example.exercise_project.R
import com.example.exercise_project.databinding.ActivityMainBinding
import com.example.exercise_project.databinding.ActivityOnBoardingBinding
import com.example.exercise_project.databinding.FragmentOnBoardingBinding

class onBoardingFragment : Fragment() {
    private var binding: FragmentOnBoardingBinding? = null
    private val bind get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = activity?.findViewById<ViewPager>(R.id.vpOnBoarding)
        bind.ibFragment1.setOnClickListener{
            viewPager?.currentItem = 1
        }
        bind.skip1.setOnClickListener{
            viewPager?.currentItem = 3
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}