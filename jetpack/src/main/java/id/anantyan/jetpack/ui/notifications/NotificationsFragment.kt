package id.anantyan.jetpack.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.anantyan.jetpack.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("NOTIFICATION", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("NOTIFICATION", "onCreateView")
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("NOTIFICATION", "onViewCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("NOTIFICATION", "onViewStateRestore")
    }

    override fun onStart() {
        super.onStart()
        Log.d("NOTIFICATION", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("NOTIFICATION", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("NOTIFICATION", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("NOTIFICATION", "onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("NOTIFICATION", "onSaveInstanceState")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("NOTIFICATION", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("NOTIFICATION", "onCreated")
    }
}