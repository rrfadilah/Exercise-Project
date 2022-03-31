package com.rizky.exercise_project.menu.ui.facebook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rizky.exercise_project.databinding.FragmentFacebookBinding

class FacebookFragment : Fragment() {
    private var _binding: FragmentFacebookBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val facebookViewModel =
            ViewModelProvider(this).get(FacebookViewModel::class.java)

        _binding = FragmentFacebookBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textFacebook
        facebookViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

