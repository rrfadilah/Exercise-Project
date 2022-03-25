package com.rizky.exercise_project.menu.ui.message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.rizky.exercise_project.R
import com.rizky.exercise_project.databinding.FragmentHomeBinding
import com.rizky.exercise_project.databinding.FragmentMessageBinding
import com.rizky.exercise_project.menu.ui.home.HomeViewModel
import com.rizky.exercise_project.menu.ui.message.data.dummy

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MessageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val adapter_doctor = MessageAdapter(dummy.messages)
//        binding.rvMessage.adapter = adapter

        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}