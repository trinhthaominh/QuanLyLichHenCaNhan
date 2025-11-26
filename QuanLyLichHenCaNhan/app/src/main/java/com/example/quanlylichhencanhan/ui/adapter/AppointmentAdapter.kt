package com.example.quanlylichhencanhan.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quanlylichhencanhan.data.model.AppointmentModel
import com.example.quanlylichhencanhan.databinding.AppointmentItemBinding

class AppointmentAdapter(
    private val onDeleteClick: (AppointmentModel) -> Unit
) : RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {

    private var appointments = listOf<AppointmentModel>()
    class AppointmentViewHolder(val binding: AppointmentItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val binding = AppointmentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AppointmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val item = appointments[position]     // đổi tên biến để rõ ràng
        holder.binding.txtFullName.text = item.fullName
        holder.binding.txtDateTime.text = item.dateTime
        holder.binding.txtPlace.text = item.place

        holder.itemView.setOnLongClickListener {
            onDeleteClick(item)
            true
        }
    }

    override fun getItemCount(): Int = appointments.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newAppointments: List<AppointmentModel>) {
        appointments = newAppointments
        notifyDataSetChanged()
    }
}
