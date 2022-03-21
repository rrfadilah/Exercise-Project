package com.example.exercise_project.MainHome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.exercise_project.R
import com.example.exercise_project.databinding.FragmentHospitalsBinding

class FragmentHospitals : Fragment() {
    lateinit var binding: FragmentHospitalsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHospitalsBinding.inflate(layoutInflater)
        return binding.root
    }
}