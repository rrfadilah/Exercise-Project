package com.example.exercise_project.ui.home.ui.doctor

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exercise_project.R
import com.example.exercise_project.data.api.home.GoodNewsResponse
import com.example.exercise_project.databinding.ListItemGoodnewsBinding


class GoodNewsAdapter(
    private val listener: EventListener,
    private var list: List<GoodNewsResponse>
) :
    RecyclerView.Adapter<GoodNewsAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ListItemGoodnewsBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<GoodNewsResponse>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemGoodnewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.tvTitle.text = item.title
        holder.binding.tvDate.text = item.date
        if (!item.image.isNullOrEmpty()) {
            Glide.with(holder.binding.root)
                .load(item.image)
                .fitCenter()
                .circleCrop()
                .placeholder(R.drawable.img_user1)
                .error(R.drawable.img_user1)
                .into(holder.binding.ivImg)
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