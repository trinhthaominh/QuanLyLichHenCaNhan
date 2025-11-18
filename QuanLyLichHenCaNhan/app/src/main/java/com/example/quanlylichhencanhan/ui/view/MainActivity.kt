package com.example.quanlylichhencanhan.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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
import kotlinx.coroutines.launch

@SuppressLint("StaticFieldLeak")
private lateinit var binding : ActivityMainBinding
class MainActivity : AppCompatActivity() {
    lateinit var viewModel: AppointmentViewModel
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
    }

    fun showAddAppointmentDialog() {
        val dialogBinding = DialoglAddAppointmentBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Thêm lịch hẹn")
            .setView(dialogBinding.root)
            .setPositiveButton("Thêm") { _, _ ->
                val fullName = dialogBinding.edtFullName.text.toString()
                val dateTime = dialogBinding.edtDateTime.text.toString()
                val place = dialogBinding.edtPlace.text.toString()
                val link = dialogBinding.edtLink.text.toString()
            }
            .setNegativeButton("Hủy", null)
            .create()
        dialog.show()
    }
}


