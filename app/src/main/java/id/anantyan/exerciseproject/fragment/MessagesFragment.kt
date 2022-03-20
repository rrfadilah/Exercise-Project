package id.anantyan.exerciseproject.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.anantyan.exerciseproject.R
import id.anantyan.exerciseproject.activity.BaseFragmentActivity
import id.anantyan.exerciseproject.activity.MessagesDetailActivity
import id.anantyan.exerciseproject.databinding.FragmentMessagesBinding
import id.anantyan.utils.viewbinding.viewBinding

class MessagesFragment : Fragment() {

    private val binding: FragmentMessagesBinding by viewBinding()
    private val context = (activity as BaseFragmentActivity)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messages, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgView.setOnClickListener {
            val intent = Intent(context, MessagesDetailActivity::class.java)

        }
    }

    override fun onStart() {
        super.onStart()
        context.supportActionBar?.title = "Messages"
    }
}