package com.example.mid.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Usrs")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "surname") val surname: String)

@Entity(tableName = "Todos")
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "priority") val priority: String,
    @ColumnInfo(name = "email") val email: String)
