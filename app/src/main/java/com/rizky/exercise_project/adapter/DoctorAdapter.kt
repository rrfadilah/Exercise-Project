package com.rizky.exercise_project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rizky.exercise_project.R
import com.tegarpenemuan.minichallenge2.model.DoctorModel

class DoctorAdapter(private val list: List<DoctorModel>): RecyclerView.Adapter<DoctorAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageAvatar = itemView.findViewById<ImageView>(R.id.ivAvatar)
        val textName = itemView.findViewById<TextView>(R.id.tvName)
        val textDesc = itemView.findViewById<TextView>(R.id.tvDescription)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.list_item_doctor, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: DoctorModel = list[position]
        holder.textName.text = item.name
        holder.textDesc.text = item.desc
        holder.imageAvatar.setImageResource(item.avatar)
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}