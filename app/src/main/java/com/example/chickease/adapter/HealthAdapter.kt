package com.example.chickease.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chickease.R
import com.example.chickease.data_class.Health

class HealthAdapter(private val healthList: List<Health>, private val onHealthClick: (Health) -> Unit) :
    RecyclerView.Adapter<HealthAdapter.HealthViewHolder>(){

    class HealthViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vaccineFlockNo: TextView = itemView.findViewById(R.id.veccflock_no)
        val vaccineDate: TextView = itemView.findViewById(R.id.vecc_date)
        val vaccineDesc: TextView = itemView.findViewById(R.id.veccdesc)
        val vaccineName: TextView = itemView.findViewById(R.id.veccname)
        val vaccineNextDate: TextView = itemView.findViewById(R.id.veccnxtdate)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.healthlist, parent, false)
        return HealthViewHolder(view)
    }

    override fun onBindViewHolder(holder: HealthViewHolder, position: Int) {
        val vaccine = healthList[position]
        holder.vaccineFlockNo.text = vaccine.veccineflockNo
        holder.vaccineDate.text = vaccine.veccineDate
        holder.vaccineName.text = vaccine.veccineName
        holder.vaccineDesc.text = vaccine.veccineDesc
        holder.vaccineNextDate.text = vaccine.veccineNextDate

        // Handle item click
        holder.itemView.setOnClickListener { onHealthClick(vaccine) }
    }

    override fun getItemCount(): Int  = healthList.size

}