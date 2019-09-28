package com.example.mid.db.repositroy

import android.app.Application
import android.content.Context
import com.example.mid.db.entities.Todo

interface ITodoRepository {
    suspend fun getTodo(): List<Todo>
    suspend fun addTodo(todo: Todo)
}