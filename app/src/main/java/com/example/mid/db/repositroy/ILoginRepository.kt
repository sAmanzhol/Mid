package com.example.mid.db.repositroy

import android.content.Context
import com.example.mid.db.entities.User

interface ILoginRepository {
    suspend fun getUser(email: String, password: String): User
    suspend fun signUp(user: User)
    suspend fun isExist(email: String): Boolean

}