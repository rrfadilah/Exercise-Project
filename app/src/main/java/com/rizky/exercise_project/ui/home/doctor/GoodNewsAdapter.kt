package com.rizky.exercise_project.ui.home.doctor

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rizky.exercise_project.R
import com.rizky.exercise_project.data.api.home.ConsultationResponse
import com.rizky.exercise_project.data.api.home.GoodNewsResponse
import com.rizky.exercise_project.data.api.home.TopRatedResponse
import com.rizky.exercise_project.databinding.ListItemConsultationBinding
import com.rizky.exercise_project.databinding.ListItemGoodnewsBinding
import com.rizky.exercise_project.databinding.ListItemTopRatedBinding
import com.rizky.exercise_project.ui.home.message.MessageModel

/**
 * com.rizky.exercise_project.ui.home.doctor
 *
 * Created by Rizky Fadilah on 25/04/22.
 * https://github.com/rizkyfadilah
 *
 */

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
                .placeholder(R.drawable.img_user_1)
                .error(R.drawable.img_user_1)
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