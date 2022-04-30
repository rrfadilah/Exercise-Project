package com.rizky.exercise_project.ui.home.hospital

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.rizky.exercise_project.databinding.FragmentHostpitalBinding
import com.rizky.exercise_project.model.HospitalModel
import com.rizky.exercise_project.network.MyDoctorApiClient
import com.rizky.exercise_project.repository.HomeRepository

class HostpitalFragment : Fragment() {
    private var _binding: FragmentHostpitalBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HospitalViewModel by viewModels {
        HospitalViewModel.Factory(HomeRepository(MyDoctorApiClient.instanceHome))
    }
    private lateinit var adapter: HospitalAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHostpitalBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        TODO: if you want to using viewmodel provider
//        viewModel = ViewModelProvider(
//            this,
//            HospitalViewModel.Factory(HomeRepository(MyDoctorApiClient.instanceHome))
//        )[HospitalViewModel::class.java]

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
