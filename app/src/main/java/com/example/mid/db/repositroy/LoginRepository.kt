package com.example.mid.db.repositroy

import android.content.Context
import com.example.mid.db.AppDatabase
import com.example.mid.db.entities.User

class LoginRepository: ILoginRepository {

    override fun signUp(application: Context, user: User) {
        return AppDatabase.getDatabase(application)?.getUserDao()?.insertUser(user)!!
    }

    override fun isExist(application: Context, email: String): Boolean {
        return AppDatabase.getDatabase(application)?.getUserDao()?.getUserWithEmail(email) != null
    }

    override fun getUser(application: Context, email: String, password: String): User {
        return AppDatabase.getDatabase(application)?.getUserDao()?.getUserWithEmailAndPassword(email, password)!!
    }
}