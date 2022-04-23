package com.example.exercise_project.UI.Home.Messages

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise_project.databinding.LinearVerticalDoctorAnakBinding
import java.util.*

class AdapterMessages(private val listener: EventListener, private var ListMessages: List<ModelMessages>):
    RecyclerView.Adapter<AdapterMessages.ViewHolder>() {

    inner class ViewHolder(val binding: LinearVerticalDoctorAnakBinding): RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<ModelMessages>) {
        this.ListMessages = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LinearVerticalDoctorAnakBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type: ModelMessages = ListMessages[position]
        holder.binding.rivImageDoctorAnak.setImageResource(type.image1)
        holder.binding.tvNameDoctorAnak.text = type.text1
        holder.binding.tvGenderDoctorAnak.text = type.text2
        holder.binding.ivEdit.setOnClickListener {
            listener.onUpdate(type)
        }
        holder.binding.ivDelete.setOnClickListener {
            listener.onDelete(type)
        }
        holder.itemView.setOnClickListener {
            listener.onClick(type)
        }
    }

    override fun getItemCount(): Int {
        return ListMessages.count()
    }

    interface EventListener {
        fun onClick(item: ModelMessages)
        fun onDelete(item: ModelMessages)
        fun onUpdate(item: ModelMessages)
    }
}