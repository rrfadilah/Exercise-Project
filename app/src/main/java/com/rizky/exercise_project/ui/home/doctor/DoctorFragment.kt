package com.rizky.exercise_project.ui.home.doctor

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
import com.google.android.material.snackbar.Snackbar
import com.rizky.exercise_project.Constant
import com.rizky.exercise_project.R
import com.rizky.exercise_project.database.MyDoctorDatabase
import com.rizky.exercise_project.databinding.FragmentDoctorBinding

class DoctorFragment : Fragment() {

    private lateinit var binding: FragmentDoctorBinding
    private val progressDialog: ProgressDialog by lazy { ProgressDialog(requireContext()) }
    private val viewModel: DoctorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDoctorBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindView()
        bindViewModel()

        val db = MyDoctorDatabase.getInstance(requireContext())
        val pref = requireContext().getSharedPreferences(
            Constant.Preferences.PREF_NAME,
            AppCompatActivity.MODE_PRIVATE
        )
        viewModel.onViewLoaded(db = db, preferences = pref)
    }

    private fun bindView() {
        binding.tvLogout.setOnClickListener {
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

        viewModel.shouldShowProfile.observe(viewLifecycleOwner) {
            binding.tvName.text = it.name
            binding.tvJob.text = it.job
            Glide.with(binding.root)
                .load(it.image)
                .circleCrop()
                .into(binding.ivImg)
        }
    }
}