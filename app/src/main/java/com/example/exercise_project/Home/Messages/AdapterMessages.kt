package com.example.exercise_project.Home.Messages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise_project.databinding.LinearVerticalDoctorAnakBinding

class AdapterMessages(private val ListMessages: List<ModelMessages>): RecyclerView.Adapter<AdapterMessages.ViewHolder>() {

    inner class ViewHolder(val binding: LinearVerticalDoctorAnakBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LinearVerticalDoctorAnakBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type: ModelMessages = ListMessages[position]
        holder.binding.rivImageDoctorAnak.setImageResource(type.image1)
        holder.binding.tvNameDoctorAnak.text = type.text1
        holder.binding.tvGenderDoctorAnak.text = type.text2
    }

    override fun getItemCount(): Int {
        return ListMessages.count()
    }
}