package com.rizky.exercise_project.Doctor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rizky.exercise_project.databinding.ListItemDoctorSpesialistBinding

class AdapterDoctor(private val listDoctor: List<ModelDoctor>): RecyclerView.Adapter<AdapterDoctor.ViewHolder>() {
    inner class ViewHolder(val binding: ListItemDoctorSpesialistBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemDoctorSpesialistBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type: ModelDoctor = listDoctor[position]
        holder.binding.ivImg.setImageResource(type.image1)
        holder.binding.tvDoctorSpecialist.text = type.text1
    }

    override fun getItemCount(): Int {
        return listDoctor.count()
    }
}