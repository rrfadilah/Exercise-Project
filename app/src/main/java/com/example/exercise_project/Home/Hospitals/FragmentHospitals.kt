package com.example.exercise_project.Home.Hospitals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.exercise_project.Home.Doctor.AdapterDoctorThird
import com.example.exercise_project.Home.Messages.AdapterMessages
import com.example.exercise_project.Home.TaskList
import com.example.exercise_project.R
import com.example.exercise_project.databinding.FragmentHospitalsBinding
import com.example.exercise_project.databinding.FragmentMessagesBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class FragmentHospitals : Fragment() {
    lateinit var binding: FragmentHospitalsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHospitalsBinding.inflate(layoutInflater)

        BottomSheetBehavior.from(binding.sheet).apply {
            peekHeight = 400
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        val adapterHospitals = AdapterHospital(TaskList.listHospitals)
        binding.rvListHospitals.adapter = adapterHospitals

        return binding.root
    }
}
