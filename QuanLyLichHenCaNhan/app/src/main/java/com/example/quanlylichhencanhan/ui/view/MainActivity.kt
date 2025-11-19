package com.example.quanlylichhencanhan.ui.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quanlylichhencanhan.R
import com.example.quanlylichhencanhan.databinding.ActivityMainBinding
import com.example.quanlylichhencanhan.databinding.DialoglAddAppointmentBinding
import com.example.quanlylichhencanhan.ui.adapter.AppointmentAdapter
import com.example.quanlylichhencanhan.ui.viewmodel.AppointmentViewModel
import com.example.quanlylichhencanhan.ui.viewmodel.DateTimeViewModel
import kotlinx.coroutines.launch
import java.util.Calendar

@SuppressLint("StaticFieldLeak")
private lateinit var binding : ActivityMainBinding
class MainActivity : AppCompatActivity() {
    lateinit var viewModel: AppointmentViewModel
    //lateinit var viewModel2: DateTimeViewModel
    private lateinit var adapter: AppointmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        adapter = AppointmentAdapter { appointment ->
            lifecycleScope.launch {
                viewModel.insertAppointment(appointment)
            }
        }
        binding.rvAppointment.adapter = adapter

        binding.rvAppointment.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this).get(AppointmentViewModel::class.java)
        viewModel.allAppointment.observe(this) { appointments ->
            adapter.setData(appointments)
        }
        binding.btnAdd.setOnClickListener {
            val dialog = showAddAppointmentDialog()
        }
        binding.edtFrom.setOnClickListener {
            showDatePickerDialog(binding.edtFrom)
        }
        binding.edtTo.setOnClickListener {
            showDatePickerDialog(binding.edtTo)
        }


    }
    fun showAddAppointmentDialog() {
        val dialogBinding = DialoglAddAppointmentBinding.inflate(layoutInflater)
        //val current = viewModel2.getCurrentTime()
        dialogBinding.edtDateTime.setOnClickListener {

            showDatePickerDialog(dialogBinding.edtDateTime)
        }
        val dialog = AlertDialog.Builder(this)
            .setTitle("Thêm lịch hẹn")
            .setView(dialogBinding.root)
            .setPositiveButton("Thêm") { _, _ ->
                val fullName = dialogBinding.edtFullName.text.toString()
                val dateTime = dialogBinding.edtDateTime.text.toString()
                val place = dialogBinding.edtPlace.text.toString()
                val link = dialogBinding.edtLink.text.toString()

//                val appointmentDate = viewModel2.parseDateTime(dateTime)
//
//                if (appointmentDate == null) {
//                    android.widget.Toast.makeText(this, "Vui lòng chọn Ngày và Giờ hợp lệ.", android.widget.Toast.LENGTH_SHORT).show()
//                } else {
//                    val appointmentMillis = appointmentDate.time
//
//                    if (appointmentMillis < current) {
//
//                        android.widget.Toast.makeText(this, "Không thể thêm lịch hẹn trong quá khứ.", android.widget.Toast.LENGTH_LONG).show()
//                    } else {
//                        android.widget.Toast.makeText(this, "Lịch hẹn hợp lệ, đang được thêm.", android.widget.Toast.LENGTH_SHORT).show()
//                    }
//                }
            }
            .setNegativeButton("Hủy", null)
            .create()
        dialog.show()

    }
    fun showDatePickerDialog(targetTextView : AppCompatTextView) {
        val calendar = Calendar.getInstance()
        val initialYear = calendar.get(Calendar.YEAR)
        val initialMonth = calendar.get(Calendar.MONTH) // Tháng bắt đầu từ 0
        val initialDate = calendar.get(Calendar.DAY_OF_MONTH)

        // Khởi tạo biến để lưu trữ ngày giờ đã chọn
        var selectedYear = initialYear
        var selectedMonth = initialMonth
        var selectedDayOfMonth = initialDate
        var selectedHour = calendar.get(Calendar.HOUR_OF_DAY)
        var selectedMinute = calendar.get(Calendar.MINUTE)

        // 1. Hiển thị DatePickerDialog
        DatePickerDialog(this, { _, year, month, dayOfMonth ->
            // Lắng nghe: Khi người dùng chọn Ngày và bấm OK
            selectedYear = year
            selectedMonth = month // Lưu trữ tháng (0-11)
            selectedDayOfMonth = dayOfMonth

            // 2. Sau khi chọn Ngày xong, hiển thị TimePickerDialog
            TimePickerDialog(this, { _, hourOfDay, minute ->
                // Lắng nghe: Khi người dùng chọn Giờ và bấm OK
                selectedHour = hourOfDay
                selectedMinute = minute

                // 3. Kết hợp kết quả và gán vào EditText
                // Lưu ý: month ở đây là 0-based, nên cần +1 khi hiển thị
                val date = String.format("%02d/%02d/%d", selectedDayOfMonth, selectedMonth + 1, selectedYear)
                val time = String.format("%02d:%02d", selectedHour, selectedMinute)
                val result = "$date $time"

                // Gán kết quả vào EditText
                targetTextView.setText(result)

            }, selectedHour, selectedMinute, true).show() // 'true' cho định dạng 24h

        }, initialYear, initialMonth, initialDate).show()
    }


 }




