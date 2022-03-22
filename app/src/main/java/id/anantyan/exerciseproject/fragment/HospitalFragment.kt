package id.anantyan.exerciseproject.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.anantyan.exerciseproject.R
import id.anantyan.exerciseproject.activity.BaseFragmentActivity
import id.anantyan.exerciseproject.databinding.FragmentHospitalBinding
import id.anantyan.jetpack.BottomNavigationActivity
import id.anantyan.utils.viewbinding.viewBinding

class HospitalFragment : Fragment() {

    private val binding: FragmentHospitalBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("HOSPITAL", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("HOSPITAL", "onCreateView")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hospital, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgView.setOnClickListener {
            val intent = Intent((activity as BaseFragmentActivity), BottomNavigationActivity::class.java)
            startActivity(intent)
        }
        Log.d("HOSPITAL", "onViewCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("HOSPITAL", "onViewStateRestore")
    }

    override fun onStart() {
        super.onStart()
        Log.d("HOSPITAL", "onStart")
    }

    override fun onResume() {
        super.onResume()
        (activity as BaseFragmentActivity).supportActionBar?.title = "Hospital"
        Log.d("HOSPITAL", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("HOSPITAL", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("HOSPITAL", "onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("HOSPITAL", "onSaveInstanceState")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("HOSPITAL", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("HOSPITAL", "onCreated")
    }
}