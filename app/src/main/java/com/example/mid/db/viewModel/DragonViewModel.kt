package com.example.mid.db.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.example.mid.db.entities.Todo
import com.example.mid.db.repositroy.ITodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DragonViewModel(
    private val repository: ITodoRepository
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val mutableLiveData = MutableLiveData<List<Todo>>()

    val liveData: MutableLiveData<List<Todo>>
        get() = mutableLiveData

    fun loadSomeData() {
        launch {
            mutableLiveData.postValue(repository.getTodo())
        }
    }

    fun addTodo(todo: Todo) {
        launch {
            repository.addTodo(todo)
        }
    }

    class Factory(private val repository: ITodoRepository) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DragonViewModel::class.java)) {
                return DragonViewModel(repository) as T
            }

            throw IllegalStateException("Cannot create an instance of viewmodel")
        }
    }

}