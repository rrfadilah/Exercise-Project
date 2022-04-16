package com.tegarpenemuan.myapplication.home.ui.doctor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tegarpenemuan.myapplication.Constant
import com.tegarpenemuan.myapplication.ui.signin.SignInActivity
import com.tegarpenemuan.myapplication.databinding.FragmentDoctorBinding


class DoctorFragment : Fragment() {

    private var _binding: FragmentDoctorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("Lifecycle", "Lifecycle NotificationsFragment onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "Lifecycle NotificationsFragment onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(DoctorViewModel::class.java)

        _binding = FragmentDoctorBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

//        textView.setOnClickListener {
//            it.findNavController().navigate(R.id.action_navigation_doctor_to_navigation_message)
//            val bundle = Bundle()
//            bundle.putString(Constant.Intent.KEY, "Value disini akan tercetak di halaman dashboard")
//            it.findNavController().navigate(R.id.action_navigation_doctor_to_navigation_message, bundle)
//        }
        val preferences = this.requireActivity()
            .getSharedPreferences(Constant.Register.PREF_REGISTER_NAME, Context.MODE_PRIVATE)

        binding.tvFullname.text = preferences.getString(Constant.Register.KEY.NAMA,"").toString()
        binding.tvPekerjaan.text = preferences.getString(Constant.Register.KEY.PEKERJAAN,"").toString()
        binding.tvEmail.text = preferences.getString(Constant.Register.KEY.EMAIL,"").toString()
        binding.tvPassword.text = preferences.getString(Constant.Register.KEY.PASSWORD,"").toString()
        binding.btnLogout.setOnClickListener {
            preferences.edit().clear().commit()
            val intent = Intent(activity, SignInActivity::class.java)
            startActivity(intent)
            this.activity?.finish()
        }

        Log.d("Lifecycle", "Lifecycle NotificationsFragment onCreateView")
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Lifecycle", "Lifecycle NotificationsFragment onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "Lifecycle NotificationsFragment onViewCreated")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "Lifecycle NotificationsFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "Lifecycle NotificationsFragment onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "Lifecycle NotificationsFragment onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("Lifecycle", "Lifecycle NotificationsFragment onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "Lifecycle NotificationsFragment onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("Lifecycle", "Lifecycle NotificationsFragment onDetach")
    }

}