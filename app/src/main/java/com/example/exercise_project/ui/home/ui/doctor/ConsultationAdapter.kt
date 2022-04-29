package com.example.exercise_project.ui.home.ui.doctor

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exercise_project.R
import com.example.exercise_project.data.api.home.ConsultationResponse
import com.example.exercise_project.databinding.ListItemConsultationBinding



class ConsultationAdapter(
    private val listener: EventListener,
    private var list: List<ConsultationResponse>
) :
    RecyclerView.Adapter<ConsultationAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ListItemConsultationBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<ConsultationResponse>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemConsultationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.tvName.text = HtmlCompat.fromHtml(item.title.orEmpty(), HtmlCompat.FROM_HTML_MODE_COMPACT)
        if (!item.icon.isNullOrEmpty()) {
            Glide.with(holder.binding.root)
                .load(item.icon)
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
        fun onClick(item: ConsultationResponse)
    }

}