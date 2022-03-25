package com.rizky.exercise_project.Doctor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rizky.exercise_project.TaskList
import com.rizky.exercise_project.databinding.FragmentDoctorBinding

class DoctorFragment : Fragment() {
    lateinit var binding: FragmentDoctorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDoctorBinding.inflate(layoutInflater)

        val adapter = AdapterDoctor(TaskList.listDoctor)
        binding.rvPilihan.adapter = adapter

        val adapterRated = AdapterDoctorSecond(TaskList.listRatedDoctor)
        binding.rvTopRated.adapter = adapterRated

        val adapterGoodNews = AdapterDoctorThird(TaskList.listGoodNews)
        binding.rvGoodNews.adapter = adapterGoodNews

        return binding.root
    }
}