package com.rizky.exercise_project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rizky.exercise_project.R
import com.rizky.exercise_project.model.LinkedInFeedModel

class LinkedInFeedAdapter(private val list: List<LinkedInFeedModel>) : RecyclerView.Adapter<LinkedInFeedAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val feedAvatar = itemView.findViewById<ImageView>(R.id.iv_img)
        val feedNama = itemView.findViewById<TextView>(R.id.tv_name)
        val feedJob = itemView.findViewById<TextView>(R.id.tv_last_message)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val view = inflater.inflate(R.layout.list_item_feed, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val feed: LinkedInFeedModel = list[position]

        holder.feedAvatar.setImageResource(feed.avatar)
        holder.feedNama.text = feed.nama
        holder.feedJob.text = feed.jobList
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}