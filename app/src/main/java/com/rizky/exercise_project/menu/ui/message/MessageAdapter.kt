package com.rizky.exercise_project.menu.ui.message

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rizky.exercise_project.R

class MessageAdapter(private val list: List<MessageModel>) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val imageAvatar = itemView.findViewById<ImageView>(R.id.iv_img)
        val textName = itemView.findViewById<TextView>(R.id.tv_name)
        val textMessage = itemView.findViewById<TextView>(R.id.tv_last_message)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val view = inflater.inflate(R.layout.list_item_message, parent, false)
        // Return a new holder instance
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val message: MessageModel = list[position]
        // Set item views based on your views and data model
        holder.imageAvatar.setImageResource(message.avatar)
        holder.textName.text = message.name
        holder.textMessage.text = message.name

    }

    override fun getItemCount(): Int {
        return list.count()
    }
}