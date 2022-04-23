package com.example.exercise_project.UI.Home.Doctor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise_project.databinding.RecyclerDoctorFirstBinding

class AdapterDoctor(private val listDoctor: List<ModelDoctor>): RecyclerView.Adapter<AdapterDoctor.ViewHolder>() {
    inner class ViewHolder(val binding: RecyclerDoctorFirstBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerDoctorFirstBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type: ModelDoctor = listDoctor[position]
        holder.binding.ivDoctor.setImageResource(type.image1)
        holder.binding.tvDoctor.text = type.text1
    }

    override fun getItemCount(): Int {
        return listDoctor.count()
    }
}