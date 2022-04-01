package com.rizky.exercise_project.LinkedIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rizky.exercise_project.Doctor.AdapterDoctor
import com.rizky.exercise_project.LinkedIn.Adapter.AdapterLinkedIn
import com.rizky.exercise_project.R
import com.rizky.exercise_project.TaskList
import com.rizky.exercise_project.databinding.FragmentDoctorBinding
import com.rizky.exercise_project.databinding.FragmentHomeLinkedInBinding


class HomeLinkedInFragment : Fragment() {
    lateinit var binding: FragmentHomeLinkedInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeLinkedInBinding.inflate(layoutInflater)

        val adapterHome = AdapterLinkedIn(TaskList.listFeeds)
        binding.rvHomeLinkedIn.adapter = adapterHome

        return binding.root
    }
}