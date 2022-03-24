package com.example.exercise_project.home.ui.doctor

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.exercise_project.Constant
import com.example.exercise_project.R
import com.example.exercise_project.databinding.FragmentDoctorBinding

class DoctorFragment : Fragment() {

    private var _binding: FragmentDoctorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("Lifecycle", "Lifecycle DoctorFragment onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "Lifecycle DoctorFragment onCreate")
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
        textView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(Constant.Intent.KEY, "Value disini akan tercetak di halaman dashboard")
            it.findNavController()
                .navigate(R.id.action_navigation_doctor_to_navigation_messages, bundle)
        }
        Log.d("Lifecycle", "Lifecycle DoctorFragment onCreateView")
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Lifecycle", "Lifecycle DoctorFragment onViewCreated")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}