package com.example.mid.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.mid.db.dao.FactDao
import com.example.mid.db.dao.UserDao
import com.example.mid.db.entities.Fact
import com.example.mid.db.entities.User

@Database(entities = [User::class, Fact::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getTodoDao(): FactDao

    companion object {
        public const val DB_NAME = "users.db"
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