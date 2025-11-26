package com.example.quanlylichhen_v2.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.quanlylichhen_v2.data.model.AppointmentModel

@Dao
interface AppointmentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAppointment(appointment: AppointmentModel)

    @Query("SELECT * FROM appointment_table")
    fun getAllAppointment(): LiveData<List<AppointmentModel>>

    @Query("SELECT * FROM appointment_table WHERE dateTime BETWEEN :from AND :to")
    fun getAppointmentByDate(from: String, to: String): LiveData<List<AppointmentModel>>

    @Delete
    suspend fun deleteAppointment(appointment: AppointmentModel)

    @Update
    suspend fun updateAppointment(appointment: AppointmentModel)

}