package com.example.exercise_project.UI.Home.Doctor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.exercise_project.UI.Home.TaskList
import com.example.exercise_project.databinding.FragmentDoctorBinding


class FragmentDoctor : Fragment() {
    lateinit var binding: FragmentDoctorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDoctorBinding.inflate(layoutInflater)

        val adapter = AdapterDoctor(TaskList.listDoctor)
        binding.rvListDoctor.adapter = adapter

        val adapterRated = AdapterDoctorSecond(TaskList.listRatedDoctor)
        binding.rvListRatedDoctor.adapter = adapterRated

        val adapterGoodNews = AdapterDoctorThird(TaskList.listGoodNews)
        binding.rvGoodNews.adapter = adapterGoodNews

        return binding.root
    }
}