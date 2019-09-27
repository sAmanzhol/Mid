package com.example.mid.db.viewModel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.example.mid.db.entities.Todo
import com.example.mid.db.repositroy.ITodoRepository

class TodoViewModel(
    private val repository: ITodoRepository
) : ViewModel() {

    private val mutableLiveData = MutableLiveData<List<Todo>>()

    val liveData: MutableLiveData<List<Todo>>
        get() = mutableLiveData

    fun loadSomeData(application: Context) {
        mutableLiveData.postValue(repository.getTodo(application))
    }

    fun addTodo(application: Context, todo: Todo) {
        repository.addTodo(application, todo)
    }

    class Factory(private val repository: ITodoRepository) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
                return TodoViewModel(repository) as T
            }

            throw IllegalStateException("Cannot create an instance of viewmodel")
        }
    }

}