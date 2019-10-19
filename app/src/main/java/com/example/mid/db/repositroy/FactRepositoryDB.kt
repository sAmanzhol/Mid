package com.example.mid.db.repositroy

import com.example.mid.db.dao.FactDao
import com.example.mid.db.entities.Fact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FactRepositoryDB(private val factDao: FactDao) : IFactRepository{
    override suspend fun addFacts(facts: List<Fact>) {
        return withContext(Dispatchers.IO) {
            factDao.insertFact(facts)!!
        }
    }

    override suspend fun getFact(): List<Fact> {
        return withContext(Dispatchers.IO) {
            factDao.getAllFacts()!!
        }
    }
}