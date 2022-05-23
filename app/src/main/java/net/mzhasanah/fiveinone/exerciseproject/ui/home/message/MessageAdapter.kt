package net.mzhasanah.fiveinone.exerciseproject.ui.home.message

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.mzhasanah.fiveinone.exerciseproject.R
import net.mzhasanah.fiveinone.exerciseproject.databinding.ListItemMessageBinding


class MessageAdapter(private val listener: EventListener, private var list: List<MessageModel>) :
    RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ListItemMessageBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<MessageModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.tvName.text = item.name
        holder.binding.tvLastMessage.text = item.lastMessage
        if (item.image.isNotEmpty()) {
            Glide.with(holder.binding.root)
                .load(item.image)
                .fitCenter()
                .circleCrop()
                .placeholder(R.drawable.img_user_1)
                .error(R.drawable.img_user_1)
                .into(holder.binding.ivImg)
        } else {
            holder.binding.ivImg.setImageResource(item.imageRes)
        }
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