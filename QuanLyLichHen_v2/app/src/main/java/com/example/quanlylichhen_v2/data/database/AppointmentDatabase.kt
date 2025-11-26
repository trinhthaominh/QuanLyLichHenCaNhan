package com.example.quanlylichhen_v2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quanlylichhen_v2.data.dao.AppointmentDao
import com.example.quanlylichhen_v2.data.model.AppointmentModel

@Database(entities = [AppointmentModel::class], version = 1, exportSchema = false)
abstract class AppointmentDatabase : RoomDatabase() {
    abstract fun appointmentDao(): AppointmentDao
    companion object {
        @Volatile
        private var INSTANCE: AppointmentDatabase? = null
        fun getDatabase(context: Context): AppointmentDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    AppointmentDatabase::class.java,
                    "appointment_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}