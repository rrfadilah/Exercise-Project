package com.rizky.exercise_project.LinkedIn.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rizky.exercise_project.Doctor.ModelDoctor
import com.rizky.exercise_project.LinkedIn.Model.ModelLinkedIn
import com.rizky.exercise_project.databinding.ListItemDoctorSpesialistBinding
import com.rizky.exercise_project.databinding.ListItemFeedsBinding

class AdapterLinkedIn(private val listFeedsHome: List<ModelLinkedIn>): RecyclerView.Adapter<AdapterLinkedIn.ViewHolder>() {
    inner class ViewHolder(val binding: ListItemFeedsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemFeedsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type: ModelLinkedIn = listFeedsHome[position]
        holder.binding.ivImg.setImageResource(type.image1)
        holder.binding.tvUsername.text = type.text1
        holder.binding.tvFollowers.text = type.text2
        holder.binding.tvDays.text = type.text3
        holder.binding.tvCaption.text = type.text4
        holder.binding.ivImgFeedsPost.setImageResource(type.image2)
        holder.binding.tvLikes.text = type.text5
        holder.binding.tvComments.text = type.text6
        holder.binding.tvShares.text = type.text7
    }

    override fun getItemCount(): Int {
        return listFeedsHome.count()
    }
}