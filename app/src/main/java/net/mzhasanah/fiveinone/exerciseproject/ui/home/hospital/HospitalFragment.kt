package net.mzhasanah.fiveinone.exerciseproject.ui.home.hospital

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import net.mzhasanah.fiveinone.exerciseproject.databinding.FragmentHospitalBinding
import net.mzhasanah.fiveinone.exerciseproject.model.HospitalModel
import net.mzhasanah.fiveinone.exerciseproject.network.MyDoctorApiClient
import net.mzhasanah.fiveinone.exerciseproject.repository.HomeRepository

class HospitalFragment : Fragment() {
    private var _binding: FragmentHospitalBinding? = null
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
        _binding = FragmentHospitalBinding.inflate(inflater, container, false)

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