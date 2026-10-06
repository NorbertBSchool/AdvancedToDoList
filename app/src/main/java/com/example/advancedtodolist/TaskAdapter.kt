package com.example.advancedtodolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(
    private val taskList: MutableList<Task>,
    private val onListChanged: () -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
        val colorBar: View = itemView.findViewById(R.id.colorBar)
        val btnDelete: Button = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.tvTitle.text = task.getTaskTitle()
        holder.tvCategory.text = task.getTaskCategory()

        val color = when (task.getTaskCategory()) {
            "Praca" -> R.color.work
            "Dom" -> R.color.home
            "Szkola" -> R.color.school
            else -> R.color.other
        }
        holder.colorBar.setBackgroundColor(
            ContextCompat.getColor(holder.itemView.context, color)
        )

        holder.btnDelete.setOnClickListener {
            val pos = holder.bindingAdapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                val deletedTitle = taskList[pos].getTaskTitle()
                taskList.removeAt(pos)
                notifyItemRemoved(pos)
                notifyItemRangeChanged(pos, taskList.size)
                Toast.makeText(
                    holder.itemView.context,
                    "Usunieto: $deletedTitle",
                    Toast.LENGTH_SHORT
                ).show()
                onListChanged()
            }
        }

        holder.itemView.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                task.getTaskTitle(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}
