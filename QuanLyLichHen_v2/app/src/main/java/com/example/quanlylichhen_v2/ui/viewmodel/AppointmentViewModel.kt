package com.example.quanlylichhen_v2.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.quanlylichhen_v2.data.database.AppointmentDatabase
import com.example.quanlylichhen_v2.data.model.AppointmentModel
import com.example.quanlylichhen_v2.repository.AppointmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppointmentViewModel(application: Application) : AndroidViewModel(application) {

    private val appointmentRepository: AppointmentRepository

    val allAppointments: LiveData<List<AppointmentModel>>

    init {
        val appointmentDao = AppointmentDatabase.getDatabase(application).appointmentDao()
        appointmentRepository = AppointmentRepository(appointmentDao)
        allAppointments = appointmentRepository.allAppointments
    }

    fun insertAppointment(appointment: AppointmentModel) = viewModelScope.launch(Dispatchers.IO){
        appointmentRepository.insertAppointment(appointment)
    }

    fun updateAppointment(appointment: AppointmentModel) = viewModelScope.launch(Dispatchers.IO) {
        appointmentRepository.updateAppointment(appointment)
    }

    fun deleteAppointment(appointment: AppointmentModel) = viewModelScope.launch(Dispatchers.IO) {
        appointmentRepository.deleteAppointment(appointment)
    }
    fun getAppointmentByDate(from: String, to: String) : LiveData<List<AppointmentModel>> {

        return appointmentRepository.getAppointmentByDate(from, to)
    }
}

