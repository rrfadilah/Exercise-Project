package com.tegarpenemuan.myapplication.ui.home.doctor

import android.app.ProgressDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.tegarpenemuan.myapplication.Constant
import com.tegarpenemuan.myapplication.database.MyDoctorDatabase
import com.tegarpenemuan.myapplication.databinding.FragmentDoctorBinding


class DoctorFragment : Fragment() {

    private lateinit var binding: FragmentDoctorBinding
    private val progressDialog: ProgressDialog by lazy { ProgressDialog(requireContext()) }
    private val viewModel: DoctorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel =
//            ViewModelProvider(this).get(DoctorViewModel::class.java)
//
//        _binding = FragmentDoctorBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
//
//        val preferences = this.requireActivity()
//            .getSharedPreferences(Constant.Register.PREF_REGISTER_NAME, Context.MODE_PRIVATE)
//
//        binding.tvFullname.text = preferences.getString(Constant.Register.KEY.NAMA,"").toString()
//        binding.tvPekerjaan.text = preferences.getString(Constant.Register.KEY.PEKERJAAN,"").toString()
//        binding.tvEmail.text = preferences.getString(Constant.Register.KEY.EMAIL,"").toString()
//        binding.tvPassword.text = preferences.getString(Constant.Register.KEY.PASSWORD,"").toString()
//        binding.btnLogout.setOnClickListener {
//            preferences.edit().clear().commit()
//            val intent = Intent(activity, SignInActivity::class.java)
//            startActivity(intent)
//            this.activity?.finish()
//        }
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
        binding.btnLogout.setOnClickListener {
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
                .into(binding.ivImg)
        }
    }
}