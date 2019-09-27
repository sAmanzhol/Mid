package com.example.mid.db.repositroy

import android.content.Context
import com.example.mid.db.entities.User

interface ILoginRepository {
    fun getUser(application: Context, email: String, password: String): User
    fun signUp(application: Context, user: User)
    fun isExist(application: Context, email: String): Boolean

}