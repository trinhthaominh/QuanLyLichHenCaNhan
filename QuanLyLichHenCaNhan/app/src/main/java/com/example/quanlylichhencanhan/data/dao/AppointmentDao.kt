package com.example.quanlylichhencanhan.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.quanlylichhencanhan.data.model.AppointmentModel

@Dao
interface AppointmentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppointment(appointment: AppointmentModel)

    // Dùng fun trả về LiveData hoặc Flow cho các truy vấn
    // mà bạn muốn UI tự động cập nhật khi dữ liệu thay đổi.
    @Query("SELECT * FROM AppointmentModel ORDER BY id DESC")
    fun getAllAppointment(): LiveData<List<AppointmentModel>>

//    @Query ("SELECT SUM(amount) FROM AppointmentModel")
//    fun getTotalExpense(): LiveData<Double>

    @Query("SELECT * FROM AppointmentModel WHERE id = :appointmentId")
    suspend fun getAppointmentById(appointmentId: Long): AppointmentModel

    @Delete
    suspend fun deleteAppointment(appointment: AppointmentModel)

    @Update
    suspend fun updateAppointment(appointment: AppointmentModel)
}