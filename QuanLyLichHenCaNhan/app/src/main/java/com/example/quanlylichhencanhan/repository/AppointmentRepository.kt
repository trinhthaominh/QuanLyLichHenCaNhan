package com.example.quanlylichhencanhan.repository

import com.example.quanlylichhencanhan.data.dao.AppointmentDao
import com.example.quanlylichhencanhan.data.model.AppointmentModel

class AppointmentRepository(private val appointmentDao: AppointmentDao) {
    val allAppointments = appointmentDao.getAllAppointment()
    suspend fun insertAppointment(appointment: AppointmentModel) {
        appointmentDao.insertAppointment(appointment)
    }

    suspend fun deleteAppointment(appointment: AppointmentModel) {
        appointmentDao.deleteAppointment(appointment)
    }

    suspend fun updateAppointment(appointment: AppointmentModel) {
        appointmentDao.updateAppointment(appointment)
    }
    suspend fun getAppointmentById(appointmentId: Long): AppointmentModel {
        return appointmentDao.getAppointmentById(appointmentId)
    }

}
