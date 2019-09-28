package com.example.mid.db.repositroy

import com.example.mid.db.dao.UserDao
import com.example.mid.db.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository(private val userDao: UserDao): ILoginRepository {

    override suspend fun signUp(user: User) {
        return withContext(Dispatchers.IO) {
             userDao.insertUser(user)!!

        }
    }

    override suspend fun isExist(email: String): Boolean {
        return withContext(Dispatchers.IO) {
           userDao.getUserWithEmail(email) != null
        }
    }

    override suspend fun getUser(email: String, password: String): User {
        return withContext(Dispatchers.IO) {
            userDao.getUserWithEmailAndPassword(email, password)!!
        }

    }
}