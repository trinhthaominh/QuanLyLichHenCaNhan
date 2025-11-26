package com.example.quanlylichhen_v2.ui.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quanlylichhen_v2.R
import com.example.quanlylichhen_v2.data.model.AppointmentModel
import com.example.quanlylichhen_v2.databinding.ActivityMainBinding
import com.example.quanlylichhen_v2.databinding.DialoglAddAppointmentBinding
import com.example.quanlylichhen_v2.ui.adapter.AppointmentAdapter
import com.example.quanlylichhen_v2.ui.viewmodel.AppointmentViewModel
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: AppointmentViewModel
    private lateinit var adapter: AppointmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = AppointmentAdapter { appointment ->
            viewModel.insertAppointment(appointment)
        }

        binding.rvAppointment.adapter = adapter

        binding.rvAppointment.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this)[AppointmentViewModel::class.java]
        viewModel.allAppointments.observe(this) { appointments ->
            adapter.setAppointments(appointments)
        }


        binding.edtFrom.setOnClickListener {
            showDatePickerDialog(binding.edtFrom)
        }
        binding.edtTo.setOnClickListener {
            showDatePickerDialog(binding.edtTo)
        }


        binding.btnAdd.setOnClickListener {
            val dialog = showAddAppointmentDialog()
        }
    }
    fun showAddAppointmentDialog() {
        val dialogBinding = DialoglAddAppointmentBinding.inflate(layoutInflater)
        dialogBinding.edtDateTime.setOnClickListener {

            showDatePickerDialog(dialogBinding.edtDateTime)
        }
        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setTitle("Thêm lịch hẹn")
            .setPositiveButton("Thêm") { _, _ ->
                val fullName = dialogBinding.edtFullName.text.toString()
                val dateTime = dialogBinding.edtDateTime.text.toString()
                val place = dialogBinding.edtPlace.text.toString()
                val linkPic = dialogBinding.edtLink.text.toString()

                val appointment = AppointmentModel(
                    fullName = fullName,
                    dateTime = dateTime,
                    place = place,
                    linkPic = linkPic
                )
                viewModel.insertAppointment(appointment)
            }
            .setNegativeButton("Hủy", null)
            .create()
        dialog.show()

    }

    fun showDatePickerDialog(targetTextView : TextView) {
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


