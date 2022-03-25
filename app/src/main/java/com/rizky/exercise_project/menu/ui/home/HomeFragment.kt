package com.rizky.exercise_project.menu.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.rizky.exercise_project.R
import com.rizky.exercise_project.adapter.DoctorAdapter
import com.rizky.exercise_project.data.DummyDoctor
import com.rizky.exercise_project.data.DummyKonsul
import com.rizky.exercise_project.databinding.FragmentHomeBinding
import com.rizky.exercise_project.konsultasi.KonsultasiAdapter
import com.rizky.exercise_project.konsultasi.KonsultasiModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.   
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapterKonsul = KonsultasiAdapter(DummyKonsul.dataKonsul)
        binding.rvKonsul.adapter = adapterKonsul

        val adapter_doctor = DoctorAdapter(DummyDoctor.Data_Doctor)
        binding.rvDoctor.adapter = adapter_doctor

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}