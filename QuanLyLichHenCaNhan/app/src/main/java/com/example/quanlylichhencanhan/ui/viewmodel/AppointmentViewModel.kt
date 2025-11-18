package com.example.quanlylichhencanhan.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.quanlylichhencanhan.repository.AppointmentRepository
import com.example.quanlylichhencanhan.data.model.AppointmentModel
import androidx.lifecycle.LiveData
import com.example.quanlylichhencanhan.data.database.AppointmentDatabase


class AppointmentViewModel(application: Application) : AndroidViewModel(application) {
    private val appointmentRepository : AppointmentRepository

    val allAppointment : LiveData<List<AppointmentModel>>

    init {
        val appointmentDao = AppointmentDatabase.getDatabase(application).appointmentDao()
        appointmentRepository = AppointmentRepository(appointmentDao)
        allAppointment = appointmentRepository.allAppointments
    }

     suspend fun insertAppointment(appointment: AppointmentModel) {
        appointmentRepository.insertAppointment(appointment)
    }

    suspend fun deleteAppointment(appointment: AppointmentModel) {
        appointmentRepository.deleteAppointment(appointment)

    }

    suspend fun updateAppointment(appointment: AppointmentModel) {
        appointmentRepository.updateAppointment(appointment)
    }

    suspend fun getAppointmentById(appointmentId: Long): AppointmentModel {
        return appointmentRepository.getAppointmentById(appointmentId)
    }

}
