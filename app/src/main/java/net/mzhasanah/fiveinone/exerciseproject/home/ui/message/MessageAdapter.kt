package net.mzhasanah.fiveinone.exerciseproject.home.ui.message

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import net.mzhasanah.fiveinone.exerciseproject.R
import com.bumptech.glide.Glide

class MessageAdapter(private val list: List<MessageModel>): RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val imageAvatar = itemView.findViewById<ImageView>(R.id.iv_img)
        val textName = itemView.findViewById<TextView>(R.id.tv_name)
        val textDesc = itemView.findViewById<TextView>(R.id.tv_last_message)
    }

    // seperti template akan terus seperti ini,
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

        Glide.with(holder.itemView.context)
            //.load("https://i.ibb.co/zJHYGBP/binarlogo.jpg")
            .load(message.image)
            .circleCrop()
            .into(holder.imageAvatar)

        // Set item views based on your views and data model
        holder.textName.text = message.name
        holder.textDesc.text = message.lastMessage

        // disini untuk kita memberikan action click pada item tsb
        holder.itemView.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Haii ${message.name}, ${message.lastMessage}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}