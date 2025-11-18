package com.example.quanlylichhencanhan.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "AppointmentModel")
data class AppointmentModel(
    @PrimaryKey
    val id: Int? = null,
    val fullName: String,
    val dateTime: Timestamp,
    val place: String,
    val linkPic: String,
)
