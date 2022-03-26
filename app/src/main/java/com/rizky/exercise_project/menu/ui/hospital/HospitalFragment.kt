package com.rizky.exercise_project.menu.ui.hospital

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rizky.exercise_project.data.DummyHospital
import com.rizky.exercise_project.databinding.FragmentHospitalBinding

class HospitalFragment : Fragment() {

    private var _binding: FragmentHospitalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val hospitalViewModel =
            ViewModelProvider(this).get(HospitalViewModel::class.java)

        _binding = FragmentHospitalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapterHospital = HospitalAdapter(DummyHospital.dataHospital)
        binding.rvHospital.adapter = adapterHospital

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}