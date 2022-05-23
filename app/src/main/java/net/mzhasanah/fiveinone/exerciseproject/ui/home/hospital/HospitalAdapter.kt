package net.mzhasanah.fiveinone.exerciseproject.ui.home.hospital

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.mzhasanah.fiveinone.exerciseproject.R
import net.mzhasanah.fiveinone.exerciseproject.databinding.ListItemHospitalBinding
import net.mzhasanah.fiveinone.exerciseproject.model.HospitalModel

class HospitalAdapter(private val listener: EventListener, private var list: List<HospitalModel>) :
    RecyclerView.Adapter<HospitalAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ListItemHospitalBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<HospitalModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemHospitalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.tvName.text = item.title
        holder.binding.tvLastMessage.text = item.address
        if (item.image.isNotEmpty()) {
            Glide.with(holder.binding.root)
                .load(item.image)
                .fitCenter()
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
        fun onClick(item: HospitalModel)
    }
}