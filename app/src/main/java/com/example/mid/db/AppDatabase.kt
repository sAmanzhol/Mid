package com.example.mid.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.mid.db.dao.TodoDao
import com.example.mid.db.dao.UserDao
import com.example.mid.db.entities.Todo
import com.example.mid.db.entities.User

@Database(entities = [User::class, Todo::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getTodoDao(): TodoDao

    companion object {
        private const val DB_NAME = "users.db"
        var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class.java) {
                    instance = Room.databaseBuilder(context,
                                AppDatabase::class.java, DB_NAME).build()
                }
            }
            return instance
        }
    }

}