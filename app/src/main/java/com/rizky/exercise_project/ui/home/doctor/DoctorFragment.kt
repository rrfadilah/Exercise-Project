package com.rizky.exercise_project.ui.home.doctor

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.rizky.exercise_project.Constant
import com.rizky.exercise_project.R
import com.rizky.exercise_project.data.api.MessagesRequest
import com.rizky.exercise_project.data.api.auth.AuthAPI
import com.rizky.exercise_project.data.api.home.ConsultationResponse
import com.rizky.exercise_project.data.api.home.GoodNewsResponse
import com.rizky.exercise_project.data.api.home.TopRatedResponse
import com.rizky.exercise_project.database.MyDoctorDatabase
import com.rizky.exercise_project.databinding.FragmentDoctorBinding
import com.rizky.exercise_project.datastore.AuthDataStoreManager
import com.rizky.exercise_project.datastore.CounterDataStoreManager
import com.rizky.exercise_project.network.ImageApiClient
import com.rizky.exercise_project.network.MyDoctorApiClient
import com.rizky.exercise_project.repository.AuthRepository
import com.rizky.exercise_project.repository.ProfileRepository
import com.rizky.exercise_project.ui.home.HomeActivity
import com.rizky.exercise_project.ui.home.message.MessageAdapter
import com.rizky.exercise_project.ui.home.message.MessageModel
import com.rizky.exercise_project.ui.onboarding.OnBoardingActivity
import com.rizky.exercise_project.ui.profile.ProfileActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DoctorFragment : Fragment() {

    private lateinit var binding: FragmentDoctorBinding
    private val progressDialog: ProgressDialog by lazy { ProgressDialog(requireContext()) }
    @Inject
    lateinit var authAPI: AuthAPI
    private val viewModel: DoctorViewModel by viewModels {
        DoctorViewModel.Factory(
            ProfileRepository(
                imageAPI = ImageApiClient.instanceImage,
                authAPI = MyDoctorApiClient.instanceAuth,
                db = MyDoctorDatabase.getInstance(requireContext()),
                prefDataStore = CounterDataStoreManager(requireContext())
            ),
            AuthRepository(
                AuthDataStoreManager(requireContext()),
                authAPI
            )
        )
    }
    private lateinit var adapterConsultation: ConsultationAdapter
    private lateinit var adapterTopRatedAdapter: TopRatedAdapter
    private lateinit var adapterGoodNewsAdapter: GoodNewsAdapter


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

    override fun onResume() {
        super.onResume()
        viewModel.getProfile()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        progressDialog.cancel()
    }

    private fun bindView() {
        binding.tvLogout.setOnClickListener {
            viewModel.logout()
        }

        binding.ivImg.setOnClickListener {
            startActivity(Intent(requireContext(), ProfileActivity::class.java))
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

        viewModel.shouldShowGetStarted.observe(viewLifecycleOwner) {
            if (it) {
                val intent = Intent(requireContext(), OnBoardingActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }
}