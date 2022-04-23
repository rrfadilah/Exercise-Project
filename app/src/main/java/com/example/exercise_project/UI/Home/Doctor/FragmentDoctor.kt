package com.example.exercise_project.UI.Home.Doctor

import android.app.ProgressDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.exercise_project.UI.Data
import com.example.exercise_project.UI.Home.TaskList
import com.example.exercise_project.database.MyDoctorDatabase
import com.example.exercise_project.databinding.FragmentDoctorBinding
import com.google.android.material.snackbar.Snackbar


class FragmentDoctor : Fragment() {
    lateinit var binding: FragmentDoctorBinding
    private val progressDialog: ProgressDialog by lazy { ProgressDialog(requireContext()) }
    private val viewModel: DoctorViewModel by viewModels()

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

        bindView()
        bindViewModel()

        val db = MyDoctorDatabase.getInstance(requireContext())
        val pref = requireContext().getSharedPreferences(
            Data.Preferences.PREF_NAME,
            AppCompatActivity.MODE_PRIVATE
        )
        viewModel.onViewLoaded(db = db, preferences = pref)

        return binding.root
    }

    private fun bindView() {
        binding.logout.setOnClickListener {
            viewModel.logout()
        }
    }

    private fun bindViewModel() {
        viewModel.shouldShowError.observe(viewLifecycleOwner) {
            val snackbar = Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG)
            snackbar.view.setBackgroundColor(Color.RED)
            snackbar.show()
        }

        viewModel.shouldShowLoading.observe(viewLifecycleOwner) {
            if (it) {
                progressDialog.setMessage("Loading...")
                progressDialog.show()
            } else {
                progressDialog.hide()
            }
        }

        viewModel.shouldShowImageProfile.observe(viewLifecycleOwner) {
            Glide.with(binding.root)
                .load(it)
                .circleCrop()
                .into(binding.rivProfile)
        }
    }
}