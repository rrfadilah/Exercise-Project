package com.example.exercise_project.UI.Home.Hospitals

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.example.exercise_project.UI.Data
import com.example.exercise_project.UI.Home.TaskList
import com.example.exercise_project.data.API.Auth.Home.GoodNewsResponse
import com.example.exercise_project.data.API.Auth.Home.HospitalResponse
import com.example.exercise_project.database.MyDoctorDatabase
import com.example.exercise_project.databinding.FragmentHospitalsBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar

class FragmentHospitals : Fragment() {
    lateinit var binding: FragmentHospitalsBinding
    private val viewModel: HospitalViewModel by viewModels()
    private lateinit var adapterHospital: AdapterHospital

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHospitalsBinding.inflate(layoutInflater, container, false)
        BottomSheetBehavior.from(binding.sheet).apply {
            peekHeight = 400
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        adapterHospital = AdapterHospital(
            listener = object : AdapterHospital.EventListener {
                override fun onClick(item: HospitalResponse) {
                    Toast.makeText(requireContext(), item.title, Toast.LENGTH_SHORT).show()
                }
            },
            listHospital = emptyList()
        )

        val db = MyDoctorDatabase.getInstance(requireContext())
        val pref = requireContext().getSharedPreferences(
            Data.Preferences.PREF_NAME,
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

    private fun bindViewModel(){
        viewModel.shouldShowError.observe(viewLifecycleOwner) {
            val snackbar = Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG)
            snackbar.view.setBackgroundColor(Color.RED)
            snackbar.show()
        }

        viewModel.shouldShowHospital.observe(viewLifecycleOwner){
            adapterHospital.updateList(it)
        }
    }
}
