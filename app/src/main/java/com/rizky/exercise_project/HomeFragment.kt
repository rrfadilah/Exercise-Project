package com.rizky.exercise_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = listOf(
            PostModel(),
            AdsModel(),
            PostModel(),
            AdsModel(),
            PostModel(),
            AdsModel(),
            AdsModel(),
            PostModel(),
        )

        val recyclerView = this.view?.findViewById<RecyclerView>(R.id.rv_post)
        val adapter = PostAdapter(requireContext(), data)

        recyclerView?.adapter = adapter
    }
}
