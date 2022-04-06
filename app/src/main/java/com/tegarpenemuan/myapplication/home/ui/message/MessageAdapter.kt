package com.tegarpenemuan.myapplication.home.ui.message

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tegarpenemuan.myapplication.R

class MessageAdapter(private val list: List<MessageModel>): RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageAvatar = itemView.findViewById<ImageView>(R.id.iv_img)
        val textName = itemView.findViewById<TextView>(R.id.tv_name)
        val textMessage = itemView.findViewById<TextView>(R.id.tv_last_message)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.list_item_messages, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message: MessageModel = list[position]
        holder.imageAvatar.setImageResource(message.image)
        holder.textName.text = message.name
        holder.textMessage.text = message.lastMessage
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}