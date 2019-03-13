package com.example.mid.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.mid.db.entities.Todo

@Dao
interface TodoDao {
    @Insert
    fun insertTodo(todo: Todo)

    @Query("SELECT * FROM Todos")
    fun getAllTodo(): List<Todo>

}