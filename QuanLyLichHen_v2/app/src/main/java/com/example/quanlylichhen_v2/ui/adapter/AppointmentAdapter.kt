package com.example.quanlylichhen_v2.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quanlylichhen_v2.data.model.AppointmentModel
import com.example.quanlylichhen_v2.databinding.AppointmentItemBinding

class AppointmentAdapter(
    private val onDeleteClick: (AppointmentModel) -> Unit
) : RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {
    private var appointments = listOf<AppointmentModel>()
    class AppointmentViewHolder(val binding: AppointmentItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val binding = AppointmentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AppointmentViewHolder(binding)

    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val appointment = appointments[position]
        holder.binding.txtFullName.text = appointment.fullName
        holder.binding.txtDateTime.text = appointment.dateTime
        holder.binding.txtPlace.text = appointment.place

        holder.itemView.setOnClickListener {
            onDeleteClick(appointment)
        }
    }

    override fun getItemCount(): Int {
        return appointments.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAppointments(newAppointments: List<AppointmentModel>) {
        appointments = newAppointments
        notifyDataSetChanged()
    }


}