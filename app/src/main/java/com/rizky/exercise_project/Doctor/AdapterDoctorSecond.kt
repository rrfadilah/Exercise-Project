package com.rizky.exercise_project.Doctor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rizky.exercise_project.databinding.ListItemTopDoctorBinding

class AdapterDoctorSecond(private val listTopRated: List<ModelDoctorSecond>): RecyclerView.Adapter<AdapterDoctorSecond.ViewHolder>() {
    inner class ViewHolder(val binding: ListItemTopDoctorBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemTopDoctorBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type: ModelDoctorSecond = listTopRated[position]
        holder.binding.ivImg.setImageResource(type.image1)
        holder.binding.tvName.text = type.text1
        holder.binding.tvSpecialist.text = type.text2
    }

    override fun getItemCount(): Int {
        return listTopRated.count()
    }
}