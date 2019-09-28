package com.example.mid.db.repositroy

import android.content.Context
import com.example.mid.db.AppDatabase
import com.example.mid.db.dao.TodoDao
import com.example.mid.db.dao.UserDao
import com.example.mid.db.entities.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoRepository(private val todoDao: TodoDao) : ITodoRepository{
    override suspend fun addTodo(todo: Todo) {
        return withContext(Dispatchers.IO) {
            todoDao.insertTodo(todo)!!
        }
    }

    override suspend fun getTodo(): List<Todo> {
        return withContext(Dispatchers.IO) {
            todoDao.getAllTodo()!!
        }
    }
}