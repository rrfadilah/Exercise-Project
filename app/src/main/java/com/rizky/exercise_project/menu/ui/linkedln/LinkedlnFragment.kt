package com.rizky.exercise_project.menu.ui.linkedln

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rizky.exercise_project.adapter.LinkedInFeedAdapter
import com.rizky.exercise_project.adapter.LinkedInListAdapter
import com.rizky.exercise_project.data.DummyLinkedlnFeed
import com.rizky.exercise_project.data.DummyLinkedlnList
import com.rizky.exercise_project.databinding.FragmentLinkedlnBinding

class LinkedlnFragment : Fragment() {

    private var _binding: FragmentLinkedlnBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val linkedlnViewModel =
            ViewModelProvider(this).get(LinkedlnViewModel::class.java)

        _binding = FragmentLinkedlnBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapterLinkedInFeed = LinkedInFeedAdapter(DummyLinkedlnFeed.dataLingkedlnFeed)
        binding.linkedInFeed.adapter = adapterLinkedInFeed

        val adapterLinkedInPost = LinkedInListAdapter(DummyLinkedlnList.dataLingkedlnList)
        binding.linkedInPost.adapter = adapterLinkedInPost


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}