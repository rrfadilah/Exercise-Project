package com.rizky.exercise_project.menu.ui.doctor

import android.app.ProgressDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.rizky.exercise_project.Constant
import com.rizky.exercise_project.data.api.home.ConsultationResponse
import com.rizky.exercise_project.data.api.home.GoodNewsResponse
import com.rizky.exercise_project.data.api.home.TopRatedResponse
import com.rizky.exercise_project.database.MyDoctorDatabase
import com.rizky.exercise_project.databinding.FragmentDoctorBinding

class DoctorFragment : Fragment() {

    private lateinit var binding: FragmentDoctorBinding
    private val progressDialog: ProgressDialog by lazy { ProgressDialog(requireContext()) }
    private val viewModel: DoctorViewModel by viewModels()
    private lateinit var adapterConsultation: ConsultationAdapter
    private lateinit var adapterTopRatedAdapter: TopRatedAdapter
    private lateinit var adapterGoodNewsAdapter: GoodNewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDoctorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterConsultation = ConsultationAdapter(
            listener = object : ConsultationAdapter.EventListener {
                override fun onClick(item: ConsultationResponse) {
                    Toast.makeText(requireContext(), item.title, Toast.LENGTH_SHORT).show()
                }
            },
            list = emptyList()
        )

        adapterTopRatedAdapter = TopRatedAdapter(
            listener = object : TopRatedAdapter.EventListener {
                override fun onClick(item: TopRatedResponse) {
                    Toast.makeText(requireContext(), item.name, Toast.LENGTH_SHORT).show()
                }
            },
            list = emptyList()
        )

        adapterGoodNewsAdapter = GoodNewsAdapter(
            listener = object : GoodNewsAdapter.EventListener {
                override fun onClick(item: GoodNewsResponse) {
                    Toast.makeText(requireContext(), item.title, Toast.LENGTH_SHORT).show()
                }
            },
            list = emptyList()
        )

        binding.rvConsultation.adapter = adapterConsultation
        binding.rvToprated.adapter = adapterTopRatedAdapter
        binding.rvGoodnews.adapter = adapterGoodNewsAdapter
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

        viewModel.shouldShowConsultation.observe(viewLifecycleOwner) {
            adapterConsultation.updateList(it)
        }

        viewModel.shouldShowTopRated.observe(viewLifecycleOwner) {
            adapterTopRatedAdapter.updateList(it)
        }

        viewModel.shouldShowGoodNews.observe(viewLifecycleOwner) {
            adapterGoodNewsAdapter.updateList(it)
        }
    }
}