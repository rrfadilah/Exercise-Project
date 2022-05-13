package com.rizky.exercise_project.menu.ui.hospital

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.rizky.exercise_project.databinding.FragmentHospitalBinding
import com.rizky.exercise_project.model.HospitalModel
import com.rizky.exercise_project.network.MyDoctorApiClient
import com.rizky.exercise_project.repository.HomeRepository

class HospitalFragment : Fragment() {

    private var _binding: FragmentHospitalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: HospitalViewModel by viewModels {
        HospitalViewModel.Factory(HomeRepository(MyDoctorApiClient.instanceHome))
    }
    private lateinit var adapter: HospitalAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HospitalAdapter(
            listener = object : HospitalAdapter.EventListener {
                override fun onClick(item: HospitalModel) {

                }

            }, list = emptyList()
        )
        binding.rvHospital.adapter = adapter
        bindView()
        bindViewModel()

        viewModel.onViewLoaded()
    }

    private fun bindView() {

    }

    private fun bindViewModel() {
        viewModel.shouldShowData.observe(viewLifecycleOwner) {
            binding.tvCount.text = "${it.size} Tersedia"
            adapter.updateList(it)
        }
    }
}