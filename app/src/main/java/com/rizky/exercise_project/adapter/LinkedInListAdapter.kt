package com.rizky.exercise_project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rizky.exercise_project.R
import com.rizky.exercise_project.model.LinkedInListModel

class LinkedInListAdapter(private val list: List<LinkedInListModel>) : RecyclerView.Adapter<LinkedInListAdapter.ViewHolder>() {
    class ViewHolder (itemView: View) :RecyclerView.ViewHolder(itemView) {
        val postAvatar = itemView.findViewById<ImageView>(R.id.ivAvatar)
        val postNama = itemView.findViewById<TextView>(R.id.tvName)
        val postDesc = itemView.findViewById<TextView>(R.id.tvDescription)
        val postCaption = itemView.findViewById<TextView>(R.id.tvCaption)
        val postImage = itemView.findViewById<ImageView>(R.id.iv_post)
        val postTag = itemView.findViewById<TextView>(R.id.tvTag)
        val postSource = itemView.findViewById<TextView>(R.id.tvSource)
        val postLike = itemView.findViewById<TextView>(R.id.tviTotal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val view = inflater.inflate(R.layout.list_item_post, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post: LinkedInListModel = list[position]

        holder.postAvatar.setImageResource(post.avatar)
        holder.postNama.text = post.nama
        holder.postDesc.text = post.statusAkun
        holder.postCaption.text = post.keterangan
        holder.postImage.setImageResource(post.gambar)
        holder.postTag.text = post.tags
        holder.postSource.text = post.website
        holder.postLike.text = post.likes
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}