package com.example.exercise_project.UI.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.exercise_project.R
import com.example.exercise_project.databinding.FragmentOnBoardingThirdBinding

class onBoardingFragmentThird : Fragment() {
    private var binding: FragmentOnBoardingThirdBinding? = null
    private val bind get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentOnBoardingThirdBinding.inflate(inflater, container, false)
        return bind.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = activity?.findViewById<ViewPager>(R.id.vpOnBoarding)
        bind.ibFragment3.setOnClickListener{
            viewPager?.currentItem = 3
        }
        bind.skip3.setOnClickListener{
            viewPager?.currentItem = 3
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}