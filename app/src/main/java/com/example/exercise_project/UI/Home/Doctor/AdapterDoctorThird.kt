package com.example.exercise_project.UI.Home.Doctor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise_project.databinding.LinearVerticalDoctor2Binding

class AdapterDoctorThird(private val ListGoodNews: List<ModelDoctorThird>): RecyclerView.Adapter<AdapterDoctorThird.ViewHolder>() {
    inner class ViewHolder(val binding: LinearVerticalDoctor2Binding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LinearVerticalDoctor2Binding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type: ModelDoctorThird = ListGoodNews[position]
        holder.binding.rivImageGoodNews.setImageResource(type.image1)
        holder.binding.tvTitleGoodNews.text = type.text1
        holder.binding.tvTimeGoodNews.text = type.text2
    }

    override fun getItemCount(): Int {
        return ListGoodNews.count()
    }
}