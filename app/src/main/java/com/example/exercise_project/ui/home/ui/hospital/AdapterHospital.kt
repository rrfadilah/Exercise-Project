package com.example.exercise_project.ui.home.ui.hospital

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exercise_project.R
import com.example.exercise_project.data.api.home.NearbyHospitalResponse
import com.example.exercise_project.databinding.ListItemHospitalBinding

class AdapterHospital(
    private val listener: EventListener,
    private var listHospital: List<NearbyHospitalResponse>
) :
    RecyclerView.Adapter<AdapterHospital.ViewHolder>() {
    inner class ViewHolder(val binding: ListItemHospitalBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<NearbyHospitalResponse>) {
        this.listHospital = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemHospitalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type = listHospital[position]
        holder.binding.tvNameHospital.text = type.title
        holder.binding.tvAlamatHospital.text = type.address
        if (!type.image.isNullOrEmpty()) {
            Glide.with(holder.binding.root)
                .load(type.image)
                .fitCenter()
                .placeholder(R.drawable.goodnews1)
                .error(R.drawable.goodnews1)
                .into(holder.binding.rivImageHospital)
        }
        holder.itemView.setOnClickListener {
            listener.onClick(type)
        }
    }

    override fun getItemCount(): Int {
        return listHospital.size
    }

    interface EventListener {
        fun onClick(item: NearbyHospitalResponse)
    }

}