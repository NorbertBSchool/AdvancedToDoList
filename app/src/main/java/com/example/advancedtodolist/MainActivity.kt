package com.example.advancedtodolist

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {  

    private lateinit var etTitle: EditText
    private lateinit var categorySpinner: Spinner
    private lateinit var btnAdd: Button
    private lateinit var rvTasks: RecyclerView
    private lateinit var tvCounter: TextView

    private val taskList = mutableListOf(
        Task("Kupic mleko i nie wrócić", "Dom"),
        Task("Skonczyc kiedyś projekt", "Praca")
    )

    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etTitle = findViewById(R.id.etTitle)
        categorySpinner = findViewById(R.id.spinnerCategory)
        btnAdd = findViewById(R.id.btnAdd)
        rvTasks = findViewById(R.id.rvTasks)
        tvCounter = findViewById(R.id.tvCounter)

        val categories = arrayOf("Praca", "Dom", "Szkola", "Inne")
        val spinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            categories
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = spinnerAdapter

        adapter = TaskAdapter(taskList) {
            updateCounter()
        }
        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = adapter
        updateCounter()

        btnAdd.setOnClickListener {
            val title = etTitle.text.toString().trim()
            val category = categorySpinner.selectedItem.toString()

            if (title.isEmpty()) {
                etTitle.error = "Wpisz nazwe zadania!"
                Toast.makeText(this, "Nazwa nie moze byc pusta!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newTask = Task(title, category)
            taskList.add(newTask)
            adapter.notifyItemInserted(taskList.size - 1)
            rvTasks.scrollToPosition(taskList.size - 1)

            etTitle.text.clear()
            categorySpinner.setSelection(0)
            Toast.makeText(this, "Dodano: $title", Toast.LENGTH_SHORT).show()
            updateCounter()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateCounter() {
        tvCounter.text = "Liczba zadan: ${taskList.size}"
    }
}
