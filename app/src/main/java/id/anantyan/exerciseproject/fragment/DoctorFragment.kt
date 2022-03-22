package id.anantyan.exerciseproject.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import id.anantyan.exerciseproject.R
import id.anantyan.exerciseproject.activity.BaseFragmentActivity
import id.anantyan.exerciseproject.databinding.FragmentDoctorBinding
import id.anantyan.exerciseproject.model.Users
import id.anantyan.jetpack.BottomNavigationActivity
import id.anantyan.utils.viewbinding.viewBinding

class DoctorFragment : Fragment() {

    private val binding: FragmentDoctorBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("DOCTOR", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("DOCTOR", "onCreateView")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("DOCTOR", "onViewCreated")
        binding.imgView.setOnClickListener {
            val intent = Intent((activity as BaseFragmentActivity), BottomNavigationActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("DOCTOR", "onViewStateRestore")
    }

    override fun onStart() {
        super.onStart()
        Log.d("DOCTOR", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("DOCTOR", "onResume")
        (activity as BaseFragmentActivity).supportActionBar?.title = getString(R.string.txt_title)
    }

    override fun onPause() {
        super.onPause()
        Log.d("DOCTOR", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("DOCTOR", "onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("DOCTOR", "onSaveInstanceState")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("DOCTOR", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("DOCTOR", "onCreated")
    }
}