package com.example.exercise_project.ui.home.ui.hospital

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.exercise_project.Constant
import com.example.exercise_project.data.api.home.NearbyHospitalResponse
import com.example.exercise_project.databinding.FragmentHospitalBinding
import com.example.exercise_project.ui.home.database.MyDoctorDatabase
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar

class HospitalFragment : Fragment() {

    lateinit var binding: FragmentHospitalBinding
    private val viewModel: HospitalViewModel by viewModels()
    private lateinit var adapterHospital: AdapterHospital

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHospitalBinding.inflate(layoutInflater, container, false)
        BottomSheetBehavior.from(binding.sheet).apply {
            peekHeight = 400
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        adapterHospital = AdapterHospital(
            listener = object : AdapterHospital.EventListener {
                override fun onClick(item: NearbyHospitalResponse) {
                    Toast.makeText(requireContext(), item.title, Toast.LENGTH_SHORT).show()
                }
            },
            listHospital = emptyList()
        )

        val db = MyDoctorDatabase.getInstance(requireContext())
        val pref = requireContext().getSharedPreferences(
            Constant.Preferences.PREF_NAME,
            AppCompatActivity.MODE_PRIVATE
        )
        viewModel.onViewLoaded(db = db, preferences = pref)

        binding.rvListHospitals.adapter = adapterHospital
        bindView()
        bindViewModel()

        return binding.root
    }

    private fun bindView() {

    }

    private fun bindViewModel() {
        viewModel.shouldShowError.observe(viewLifecycleOwner) {
            val snackbar = Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG)
            snackbar.view.setBackgroundColor(Color.RED)
            snackbar.show()
        }

        viewModel.shouldShowHospital.observe(viewLifecycleOwner) {
            adapterHospital.updateList(it)
        }
    }
}