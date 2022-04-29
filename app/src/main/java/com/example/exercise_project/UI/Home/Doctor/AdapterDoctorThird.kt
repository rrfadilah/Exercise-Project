package com.example.exercise_project.UI.Home.Doctor

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exercise_project.R
import com.example.exercise_project.data.API.Auth.Home.GoodNewsResponse
import com.example.exercise_project.databinding.LinearVerticalDoctor2Binding

class AdapterDoctorThird(
    private val listener: EventListener,
    private var list: List<GoodNewsResponse>
) :
    RecyclerView.Adapter<AdapterDoctorThird.ViewHolder>() {
    inner class ViewHolder(val binding: LinearVerticalDoctor2Binding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<GoodNewsResponse>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            LinearVerticalDoctor2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.tvNameHospital.text = item.title
        holder.binding.tvAlamatHospital.text = item.date
        if (!item.image.isNullOrEmpty()) {
            Glide.with(holder.binding.root)
                .load(item.image)
                .fitCenter()
                .placeholder(R.drawable.goodnews1)
                .error(R.drawable.goodnews1)
                .into(holder.binding.rivImageHospital)
        }
        holder.itemView.setOnClickListener {
            listener.onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface EventListener {
        fun onClick(item: GoodNewsResponse)
    }

}