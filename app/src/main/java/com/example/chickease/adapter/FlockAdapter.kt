package com.example.chickease.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chickease.R
import com.example.chickease.data_class.Flock

class FlockAdapter(private val flockList: List<Flock>, private val onFlockClick: (Flock) -> Unit) :
    RecyclerView.Adapter<FlockAdapter.FlockViewHolder>() {

    class FlockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val flockNo: TextView = itemView.findViewById(R.id.flock_no)
        val flockAge: TextView = itemView.findViewById(R.id.flock_age)
        val flockType: TextView = itemView.findViewById(R.id.flock_type)
        val flockSize: TextView = itemView.findViewById(R.id.flock_size)
        val dateOfEstablishment: TextView = itemView.findViewById(R.id.flock_date_of_establishment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlockViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.flocklist, parent, false)
        return FlockViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlockViewHolder, position: Int) {
        val flock = flockList[position]
        holder.flockNo.text = flock.flockNo
        holder.flockAge.text = flock.flockAge
        holder.flockType.text = flock.flockType
        holder.flockSize.text = flock.flockSize
        holder.dateOfEstablishment.text = flock.dateOfEstablishment

        // Handle item click
        holder.itemView.setOnClickListener { onFlockClick(flock) }
    }

    override fun getItemCount(): Int = flockList.size
}