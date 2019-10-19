package com.example.mid.db.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.mid.db.entities.Fact
import com.example.mid.db.repositroy.IFactRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FactViewModel(
    private val repository: IFactRepository
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val mutableLiveData = MutableLiveData<List<Fact>>()

    val liveData: MutableLiveData<List<Fact>>
        get() = mutableLiveData

    fun loadSomeData() {
        launch {
            mutableLiveData.postValue(repository.getFact())
        }
    }


    class Factory(private val repository: IFactRepository) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FactViewModel::class.java)) {
                return FactViewModel(repository) as T
            }

            throw IllegalStateException("Cannot create an instance of viewmodel")
        }
    }

}