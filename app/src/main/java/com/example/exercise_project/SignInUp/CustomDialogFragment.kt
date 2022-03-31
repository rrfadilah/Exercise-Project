package com.example.exercise_project.SignInUp

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.exercise_project.R
import com.example.exercise_project.databinding.FragmentCustomDialogBinding

class CustomDialogFragment : DialogFragment() {
    lateinit var binding: FragmentCustomDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCustomDialogBinding.inflate(inflater,container,false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.btnCustomFragment.setOnClickListener {
            dialog?.dismiss()
        }


        return binding.root

    }
}