package com.rizky.exercise_project.Doctor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdapterDoctorThird(private val listGoodNews: List<ModelDoctorThird>): RecyclerView.Adapter<AdapterDoctorThird.ViewHolder>() {
    inner class ViewHolder(val binding: RecyclerDoctorThirdBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerDoctorThirdBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type: ModelDoctorThird = listGoodNews[position]
        holder.binding.ivDoctor.setImageResource(type.image1)
        holder.binding.tvDoctor.text = type.text1
        holder.binding.tvDoctor.text = type.text2
    }

    override fun getItemCount(): Int {
        return listGoodNews.count()
    }
}