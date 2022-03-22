package id.anantyan.exerciseproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.anantyan.exerciseproject.databinding.ListItemMessagesBinding
import id.anantyan.exerciseproject.model.Messages

class MessagesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var oldMessages: MutableList<Messages> = mutableListOf()

    val differ: (List<Messages>) -> Unit = {
        val diffUtil = DiffUtilMessages(oldMessages, it)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldMessages.clear()
        oldMessages.addAll(it)
        diffResult.dispatchUpdatesTo(this)
    }

    private var onClick: ((Int, Messages) -> Unit)? = null
    private var onLongClick: ((Int, Messages) -> Unit)? = null

    fun setOnClick(listener: (Int, Messages) -> Unit) { onClick = listener }
    fun setOnLongClick(listener: (Int, Messages) -> Unit) { onLongClick = listener }

    inner class ViewHolder(val binding: ListItemMessagesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onClick?.let {
                    it(adapterPosition, oldMessages[adapterPosition])
                }
            }
            itemView.setOnLongClickListener {
                onLongClick?.let {
                    it(adapterPosition, oldMessages[adapterPosition])
                }
                true
            }
        }

        fun bind(items: Messages) {
            binding.txtSender.text = items.senderName
            binding.txtMessages.text = items.message
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ListItemMessagesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ViewHolder
        val items = oldMessages[position]
        holder.bind(items)
    }

    override fun getItemCount(): Int {
        return oldMessages.size
    }
}

class DiffUtilMessages(
    private val oldMessages: List<Messages>,
    private val newMessages: List<Messages>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldMessages.size
    }

    override fun getNewListSize(): Int {
        return newMessages.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldMessages[oldItemPosition].senderName == newMessages[newItemPosition].senderName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldMessages[oldItemPosition].senderName != newMessages[newItemPosition].senderName -> {
                false
            }
            oldMessages[oldItemPosition].fromName != newMessages[newItemPosition].fromName -> {
                false
            }
            oldMessages[oldItemPosition].message != newMessages[newItemPosition].message -> {
                false
            }
            else -> true
        }
    }
}