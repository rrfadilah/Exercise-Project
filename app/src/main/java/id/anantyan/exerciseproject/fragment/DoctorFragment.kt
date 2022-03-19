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
import id.anantyan.exerciseproject.model.DataDummy
import id.anantyan.exerciseproject.utils.Constant.PASSING_TO_DOCTOR_FRAGMENT
import id.anantyan.exerciseproject.utils.viewbinding.viewBinding

class DoctorFragment : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels()
    private val binding: FragmentDoctorBinding by viewBinding()

    companion object {
        @JvmStatic
        fun newInstance(item: DataDummy?): DoctorFragment {
            return DoctorFragment().apply {
                this.arguments = Bundle().apply {
                    putParcelable(PASSING_TO_DOCTOR_FRAGMENT, item)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getParcelable<DataDummy>(PASSING_TO_DOCTOR_FRAGMENT)?.let {
            viewModel.setItem(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.item.observe(viewLifecycleOwner) {
            Toast.makeText((activity as BaseFragmentActivity), it.email, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as BaseFragmentActivity).supportActionBar?.setTitle(R.string.txt_title)
    }
}