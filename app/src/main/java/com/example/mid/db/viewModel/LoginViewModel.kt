package com.example.mid.db.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.example.mid.db.entities.Todo
import com.example.mid.db.entities.User
import com.example.mid.db.repositroy.ILoginRepository
import com.example.mid.db.repositroy.ITodoRepository

class LoginViewModel (
    private val repository: ILoginRepository
) : ViewModel() {

    private val mutableLiveData = MutableLiveData<User>()
    private val isExistMutableLiveData = MutableLiveData<Boolean>()

    val liveData: MutableLiveData<User>
        get() = mutableLiveData

    val isExistLiveData: MutableLiveData<Boolean>
        get() = isExistMutableLiveData

    fun loadUser(application: Context, email: String, password: String) {
        mutableLiveData.postValue(repository.getUser(application, email, password))
    }

    fun loadUserWithEmail(application: Context, email: String) {
        isExistMutableLiveData.postValue(repository.isExist(application, email))
    }

    fun signUp(application: Context, user: User) {
        repository.signUp(application, user)
    }

    class Factory(private val repository: ILoginRepository) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel(repository) as T
            }

            throw IllegalStateException("Cannot create an instance of viewmodel")
        }
    }

}