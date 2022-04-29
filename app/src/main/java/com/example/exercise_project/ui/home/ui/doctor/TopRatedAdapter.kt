package com.example.exercise_project.ui.home.ui.doctor

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exercise_project.R
import com.example.exercise_project.data.api.home.TopRatedResponse
import com.example.exercise_project.databinding.ListItemTopRatedBinding


class TopRatedAdapter(
    private val listener: EventListener,
    private var list: List<TopRatedResponse>
) :
    RecyclerView.Adapter<TopRatedAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ListItemTopRatedBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<TopRatedResponse>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemTopRatedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.tvName.text = item.name
        holder.binding.tvSpecialist.text = item.specialist
        if (!item.image.isNullOrEmpty()) {
            Glide.with(holder.binding.root)
                .load(item.image)
                .fitCenter()
                .circleCrop()
                .placeholder(R.drawable.img_user1)
                .error(R.drawable.img_user1)
                .into(holder.binding.ivImg)
        }
        holder.binding.rbRate.rating = (item.rating ?: 0.0).toFloat()
        holder.itemView.setOnClickListener {
            listener.onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface EventListener {
        fun onClick(item: TopRatedResponse)
    }

}