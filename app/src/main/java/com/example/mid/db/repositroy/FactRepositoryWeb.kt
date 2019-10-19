package com.example.mid.db.repositroy

import android.widget.Toast
import com.example.kinokotlin.api.RetrofitClient
import com.example.mid.api.JsonPlaceHolderApi
import com.example.mid.db.dao.FactDao
import com.example.mid.db.entities.Fact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FactRepositoryWeb(private val factDao: FactDao) : IFactRepository{
    internal var service = RetrofitClient.retrofitInstance.create(JsonPlaceHolderApi::class.java)



    override suspend fun addFacts(facts: List<Fact>) {}

    override suspend fun getFact(): List<Fact> {
        return withContext(Dispatchers.IO) {
            factDao.insertFact( service.getFacts().await())!!
            service.getFacts().await()
        }
    }

}