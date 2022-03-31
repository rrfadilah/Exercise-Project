package com.rizky.exercise_project.menu.ui.lingkedln

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rizky.exercise_project.databinding.FragmentLinkedlnBinding

class LinkedlnFragment : Fragment() {

    private var _binding: FragmentLinkedlnBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(LinkedlnViewModel::class.java)

        _binding = FragmentLinkedlnBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textLinkedln
//        LinkedlnViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}