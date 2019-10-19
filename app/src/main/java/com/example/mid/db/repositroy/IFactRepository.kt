package com.example.mid.db.repositroy

import com.example.mid.db.entities.Fact

interface IFactRepository {
    suspend fun getFact(): List<Fact>
    suspend fun addFacts(facts: List<Fact>)
}