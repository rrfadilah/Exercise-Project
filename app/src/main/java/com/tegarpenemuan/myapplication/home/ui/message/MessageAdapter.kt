package com.tegarpenemuan.myapplication.home.ui.message

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tegarpenemuan.myapplication.R
import com.tegarpenemuan.myapplication.databinding.ListItemMessagesBinding

class MessageAdapter(private val listener: EventListener, private var list: List<MessageModel>) :
    RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ListItemMessagesBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<MessageModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemMessagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.tvName.text = item.name
        holder.binding.tvLastMessage.text = item.lastMessage
        holder.binding.ivImg.setImageResource(item.imageRes)
        Glide
            .with(holder.itemView.context)
            .load(item.image)
            .centerCrop()
            .circleCrop()
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.error)
            .into(holder.binding.ivImg)

        holder.binding.ivUpdate.setOnClickListener {
            listener.onUpdate(item)
        }

        holder.binding.ivCancel.setOnClickListener {
            listener.onDelete(item)
        }
        holder.itemView.setOnClickListener {
            listener.onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface EventListener {
        fun onClick(item: MessageModel)
        fun onDelete(item: MessageModel)
        fun onUpdate(item: MessageModel)
    }
}