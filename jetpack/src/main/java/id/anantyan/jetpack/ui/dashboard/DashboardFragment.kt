package id.anantyan.jetpack.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.anantyan.jetpack.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("DASHBOARD", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("DASHBOARD", "onCreateView")
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("DASHBOARD", "onViewCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("DASHBOARD", "onViewStateRestore")
    }

    override fun onStart() {
        super.onStart()
        Log.d("DASHBOARD", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("DASHBOARD", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("DASHBOARD", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("DASHBOARD", "onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("DASHBOARD", "onSaveInstanceState")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("DASHBOARD", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("DASHBOARD", "onCreated")
    }
}