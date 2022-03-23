package com.example.exercise_project.Home.Hospitals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise_project.Home.Doctor.ModelDoctorThird
import com.example.exercise_project.databinding.LinearVerticalDoctor2Binding
import com.example.exercise_project.databinding.LinearVerticalHospitalBinding

class AdapterHospital(private val listHospital: List<ModelHospital>): RecyclerView.Adapter<AdapterHospital.ViewHolder>() {
    inner class ViewHolder(val binding: LinearVerticalDoctor2Binding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LinearVerticalDoctor2Binding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type: ModelHospital = listHospital[position]
        holder.binding.rivImageGoodNews.setImageResource(type.image1)
        holder.binding.tvTitleGoodNews.text = type.text1
        holder.binding.tvTimeGoodNews.text = type.text2
    }

    override fun getItemCount(): Int {
        return listHospital.count()
    }
}