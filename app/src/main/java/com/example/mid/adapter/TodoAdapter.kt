package com.example.mid.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mid.R
import com.example.mid.db.entities.Todo
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(private val todoList: List<Todo>)
    : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = TodoViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false))

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bindTodo(todoList[position])
    }

    override fun getItemCount() = todoList.size

    inner class TodoViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bindTodo(todo: Todo) {
            if (todo.priority == "high"){
                view.part.setBackgroundResource(R.color.red)
            } else if (todo.priority == "medium") {
                view.part.setBackgroundResource(R.color.yellow)
            }

            view.title.text = todo.title
            view.priority.text = todo.priority
            view.content.text = todo.content
        }
    }
}