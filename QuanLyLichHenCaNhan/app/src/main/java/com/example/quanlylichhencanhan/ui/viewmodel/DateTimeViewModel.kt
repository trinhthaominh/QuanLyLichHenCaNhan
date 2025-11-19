package com.example.quanlylichhencanhan.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class DateTimeViewModel : ViewModel() {

    private val _openDateTimeDialog = MutableLiveData<Int>()
    val openDateTimeDialog: LiveData<Int> = _openDateTimeDialog

    fun onEditTextClicked(id: Int) {
        _openDateTimeDialog.value = id
    }

    fun onDateTimeSelected(id: Int, dateTime: String) {
        // xử lý nghiệp vụ sau khi user chọn xong
    }

    fun getCurrentTime(): Long {
        val calendar = Calendar.getInstance()

        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }

    fun parseDateTime(dateTimeString: String): Date? {
        // Đảm bảo định dạng phải khớp chính xác với kết quả từ Date/Time Picker
        return try {
            val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            format.parse(dateTimeString)
        } catch (e: Exception) {
            null // Trả về null nếu chuỗi không hợp lệ
        }
    }
}
