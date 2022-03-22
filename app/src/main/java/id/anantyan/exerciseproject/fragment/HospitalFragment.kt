package id.anantyan.exerciseproject.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.anantyan.exerciseproject.R
import id.anantyan.exerciseproject.activity.BaseFragmentActivity
import id.anantyan.exerciseproject.databinding.FragmentHospitalBinding
import id.anantyan.utils.viewbinding.viewBinding

class HospitalFragment : Fragment() {

    private val binding: FragmentHospitalBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hospital, container, false)
    }

    override fun onResume() {
        super.onResume()
        (activity as BaseFragmentActivity).supportActionBar?.title = "Hospital"
    }
}