package com.example.mid.db.repositroy

import android.content.Context
import com.example.mid.db.AppDatabase
import com.example.mid.db.entities.Todo

class TodoRepository : ITodoRepository{
    override fun getTodo(application: Context): List<Todo> {
        return AppDatabase.getDatabase(application)?.getTodoDao()?.getAllTodo()!!
    }
}