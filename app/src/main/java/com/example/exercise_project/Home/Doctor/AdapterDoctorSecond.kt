package com.example.exercise_project.Home.Doctor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise_project.databinding.LinearVerticalDoctor1Binding

class AdapterDoctorSecond(private val listRatedDoctor: List<ModelDoctorSecond>): RecyclerView.Adapter<AdapterDoctorSecond.ViewHolder>() {
    inner class ViewHolder(val binding: LinearVerticalDoctor1Binding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LinearVerticalDoctor1Binding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type: ModelDoctorSecond = listRatedDoctor[position]
        holder.binding.rivProfiledoctor.setImageResource(type.image1)
        holder.binding.tvRatedDoctorName.text = type.text1
        holder.binding.tvRatedDoctorSpecial.text = type.text2
    }

    override fun getItemCount(): Int {
        return listRatedDoctor.count()
    }
}