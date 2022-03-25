package com.rizky.exercise_project.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rizky.exercise_project.databinding.FragmentDoctorBinding
import com.rizky.exercise_project.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {
    lateinit var binding: FragmentMessageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMessageBinding.inflate(layoutInflater)
        return binding.root
    }
}