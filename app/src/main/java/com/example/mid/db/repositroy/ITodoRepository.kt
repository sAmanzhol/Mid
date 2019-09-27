package com.example.mid.db.repositroy

import android.app.Application
import android.content.Context
import com.example.mid.db.entities.Todo

interface ITodoRepository {
    fun getTodo(application: Context): List<Todo>
}