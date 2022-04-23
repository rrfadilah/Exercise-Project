package com.example.exercise_project.UI.onBoarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.exercise_project.UI.Home.ActivityForHome
import com.example.exercise_project.UI.SignInUp.SignInActivity
import com.example.exercise_project.databinding.FragmentOnBoardingFourthBinding

class onBoardingFragmentFourth : Fragment() {
    private var binding: FragmentOnBoardingFourthBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingFourthBinding.inflate(inflater, container, false)
        return binding!!.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.btnOnboardStart.setOnClickListener{
                val intent = Intent(activity, ActivityForHome::class.java)
                startActivity(intent)
        }
        binding!!.btnOnboardSignin.setOnClickListener{
            val intent = Intent(activity, SignInActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    fun onBoardingFinished(): Boolean{
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }
}