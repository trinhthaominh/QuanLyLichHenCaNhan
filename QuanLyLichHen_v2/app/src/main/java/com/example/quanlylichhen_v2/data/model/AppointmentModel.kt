package com.example.quanlylichhen_v2.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointment_table")
data class AppointmentModel(
    @PrimaryKey(autoGenerate = true)
    val appointmentId: Int? = null,
    val fullName: String,
    val dateTime: String,
    val place: String,
    val linkPic: String,
)