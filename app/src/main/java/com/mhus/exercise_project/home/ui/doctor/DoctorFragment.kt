package net.mhus.fiveinone.exerciseproject.home.ui.doctor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.mhus.exercise_project.databinding.FragmentDoctorBinding

class DoctorFragment : Fragment() {

    private var _binding: FragmentDoctorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val doctorViewModel =
            ViewModelProvider(this).get(DoctorViewModel::class.java)

        _binding = FragmentDoctorBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDoctor
        doctorViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}