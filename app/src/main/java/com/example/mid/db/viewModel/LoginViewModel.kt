package com.example.mid.db.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.example.mid.db.entities.Todo
import com.example.mid.db.entities.User
import com.example.mid.db.repositroy.ILoginRepository
import com.example.mid.db.repositroy.ITodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel (
    private val repository: ILoginRepository
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val mutableLiveData = MutableLiveData<User>()
    private val isExistMutableLiveData = MutableLiveData<Boolean>()

    val liveData: MutableLiveData<User>
        get() = mutableLiveData

    val isExistLiveData: MutableLiveData<Boolean>
        get() = isExistMutableLiveData

    fun loadUser( email: String, password: String) {
        launch {
            mutableLiveData.postValue(repository.getUser( email, password))
        }
    }

    fun loadUserWithEmail( email: String) {
        launch {
            isExistMutableLiveData.postValue(repository.isExist( email))
        }
    }

    fun signUp( user: User) {
        launch {
            repository.signUp( user)
        }
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