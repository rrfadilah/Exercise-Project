package id.anantyan.exerciseproject.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.anantyan.exerciseproject.R
import id.anantyan.exerciseproject.databinding.FragmentVideoBinding
import id.anantyan.exerciseproject.utils.viewbinding.viewBinding

class VideoFragment : Fragment() {

    private val binding: FragmentVideoBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}