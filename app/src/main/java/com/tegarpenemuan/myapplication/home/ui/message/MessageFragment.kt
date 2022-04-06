package com.tegarpenemuan.myapplication.home.ui.message

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tegarpenemuan.myapplication.Constant
import com.tegarpenemuan.myapplication.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("Lifecycle", "Lifecycle DashboardFragment onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "Lifecycle DashboardFragment onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(MessageViewModel::class.java)

        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        Log.d("Lifecycle", "Lifecycle DashboardFragment onCreateView")
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Lifecycle", "Lifecycle DashboardFragment onViewCreated")

//        val data = arguments?.getString(Constant.Intent.KEY)
//        binding.textDashboard.text = "Dashboard akan mencetak ::: $data"
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "Lifecycle DashboardFragment onViewCreated")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "Lifecycle DashboardFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "Lifecycle DashboardFragment onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "Lifecycle DashboardFragment onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "Lifecycle DashboardFragment onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("Lifecycle", "Lifecycle DashboardFragment onDetach")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("Lifecycle", "Lifecycle DashboardFragment onDestroyView")
    }

}