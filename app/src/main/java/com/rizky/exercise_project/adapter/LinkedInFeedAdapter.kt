package com.rizky.exercise_project.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rizky.exercise_project.model.LinkedInFeedModel

class LinkedInFeedAdapter(private val list: List<LinkedInFeedModel>) : RecyclerView.Adapter<LinkedInFeedAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val feed: LinkedInFeedModel = list[position]
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}