package id.anantyan.jetpack.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.anantyan.jetpack.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("HOME", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("HOME", "onCreateView")
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("HOME", "onViewCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("HOME", "onViewStateRestore")
    }

    override fun onStart() {
        super.onStart()
        Log.d("HOME", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("HOME", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("HOME", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("HOME", "onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("HOME", "onSaveInstanceState")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("HOME", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("HOME", "onCreated")
    }
}