package com.example.exercise_project.UI.Home.Hospitals

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exercise_project.R
import com.example.exercise_project.data.API.Auth.Home.HospitalResponse
import com.example.exercise_project.databinding.LinearVerticalDoctor2Binding
import java.util.*

class AdapterHospital(private val listener: EventListener, private var listHospital: List<HospitalResponse>):
    RecyclerView.Adapter<AdapterHospital.ViewHolder>() {
    inner class ViewHolder(val binding: LinearVerticalDoctor2Binding): RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<HospitalResponse>) {
        this.listHospital = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LinearVerticalDoctor2Binding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type = listHospital[position]
        holder.binding.tvNameHospital.text = type.title
        holder.binding.tvAlamatHospital.text = type.address
        if (!type.image.isNullOrEmpty()) {
            Glide.with(holder.binding.root)
                .load(type.image)
                .fitCenter()
                .circleCrop()
                .placeholder(R.drawable.goodnews1)
                .error(R.drawable.goodnews1)
                .into(holder.binding.rivImageHospital)
        }
        holder.itemView.setOnClickListener {
            listener.onClick(type)
        }
    }

    override fun getItemCount(): Int {
        return listHospital.count()
    }

    interface EventListener {
        fun onClick(item: HospitalResponse)
    }

}