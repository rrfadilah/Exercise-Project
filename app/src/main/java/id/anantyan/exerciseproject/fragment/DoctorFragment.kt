package id.anantyan.exerciseproject.fragment

import android.os.Bundle
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
import id.anantyan.utils.viewbinding.viewBinding

class DoctorFragment : Fragment() {

    private val binding: FragmentDoctorBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor, container, false)
    }

    override fun onResume() {
        super.onResume()
        (activity as BaseFragmentActivity).supportActionBar?.title = getString(R.string.txt_title)
    }
}