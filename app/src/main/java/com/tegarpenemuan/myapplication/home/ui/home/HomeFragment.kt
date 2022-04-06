package com.tegarpenemuan.myapplication.home.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tegarpenemuan.myapplication.databinding.FragmentDoctorBinding

class HomeFragment : Fragment() {

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
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentDoctorBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
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