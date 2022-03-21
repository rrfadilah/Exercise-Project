package com.example.exercise_project.onBoardingFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.viewpager.widget.ViewPager
import com.example.exercise_project.R
import com.example.exercise_project.databinding.ActivityOnBoardingBinding
import com.example.exercise_project.databinding.FragmentOnBoardingBinding
import com.example.exercise_project.databinding.FragmentOnBoardingSecondBinding

class onBoardingFragmentSecond : Fragment() {
    private var binding: FragmentOnBoardingSecondBinding? = null
    private val bind get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentOnBoardingSecondBinding.inflate(inflater, container, false)
        return bind.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = activity?.findViewById<ViewPager>(R.id.vpOnBoarding)
        bind.ibFragment2.setOnClickListener{
            viewPager?.currentItem = 2
        }
        bind.skip2.setOnClickListener{
            viewPager?.currentItem = 3
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}