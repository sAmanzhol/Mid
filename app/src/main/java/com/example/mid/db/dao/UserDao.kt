package com.example.mid.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.mid.db.entities.User

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Query("SELECT * FROM Usrs WHERE email LIKE :email")
    fun getUserWithEmail(email: String): User

    @Query("SELECT * FROM Usrs WHERE email LIKE :email AND password LIKE :password")
    fun getUserWithEmailAndPassword(email: String?, password: String): User

}