package com.rizky.exercise_project.Doctor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rizky.exercise_project.databinding.ListItemNewsBinding

class AdapterDoctorThird(private val listGoodNews: List<ModelDoctorThird>): RecyclerView.Adapter<AdapterDoctorThird.ViewHolder>() {
    inner class ViewHolder(val binding: ListItemNewsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type: ModelDoctorThird = listGoodNews[position]
        holder.binding.ivImg.setImageResource(type.image1)
        holder.binding.tvTitle.text = type.text1
        holder.binding.tvWhen.text = type.text2
    }

    override fun getItemCount(): Int {
        return listGoodNews.count()
    }
}