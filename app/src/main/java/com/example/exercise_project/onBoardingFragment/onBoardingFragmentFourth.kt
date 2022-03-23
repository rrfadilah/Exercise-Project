package com.example.exercise_project.onBoardingFragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.exercise_project.GetStartedActivity
import com.example.exercise_project.Home.ActivityForHome
import com.example.exercise_project.MainActivity
import com.example.exercise_project.R
import com.example.exercise_project.SignInUp.SignInActivity
import com.example.exercise_project.SignInUp.SignUpActivity
import com.example.exercise_project.databinding.ActivitySignUpBinding
import com.example.exercise_project.databinding.FragmentOnBoardingFourthBinding
import com.example.exercise_project.databinding.FragmentOnBoardingThirdBinding

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
}