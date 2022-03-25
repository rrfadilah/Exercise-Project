package com.rizky.exercise_project.menu.ui.hospital

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.rizky.exercise_project.R

class HospitalAdapter(private val list: List<HospitalModel>) :
    RecyclerView.Adapter<HospitalAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageAvatar = itemView.findViewById<ImageView>(R.id.ivHosImage)
        val textName = itemView.findViewById<TextView>(R.id.tvHosName)
        val textDesc = itemView.findViewById<TextView>(R.id.tvHosDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val view = inflater.inflate(R.layout.list_hospital, parent, false)
        // Return a new holder instance
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val hospital: HospitalModel = list[position]
        holder.imageAvatar.setImageResource(hospital.avatar)
        holder.textName.text = hospital.name
        holder.textDesc.text = hospital.desc

        // disini untuk kita memberikan action click pada item tsb
        holder.itemView.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Haii ${hospital.name}, ${hospital.desc}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}