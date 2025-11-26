package com.example.quanlylichhen_v2.repository

import androidx.lifecycle.LiveData
import com.example.quanlylichhen_v2.data.dao.AppointmentDao
import com.example.quanlylichhen_v2.data.model.AppointmentModel

class AppointmentRepository(private val appointmentDao: AppointmentDao)  {
    val allAppointments : LiveData<List<AppointmentModel>>
        = appointmentDao.getAllAppointment()

    suspend fun insertAppointment(appointment: AppointmentModel) {
        appointmentDao.insertAppointment(appointment)
    }

    suspend fun deleteAppointment(appointment: AppointmentModel) {
        appointmentDao.deleteAppointment(appointment)
    }

    suspend fun updateAppointment(appointment: AppointmentModel) {
        appointmentDao.updateAppointment(appointment)
    }
    fun getAppointmentByDate(from: String, to: String): LiveData<List<AppointmentModel>> {
        return appointmentDao.getAppointmentByDate(from, to)
    }

}