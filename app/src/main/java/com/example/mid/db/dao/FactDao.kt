package com.example.mid.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.mid.db.entities.Fact

@Dao
interface FactDao {
    @Insert
    @JvmSuppressWildcards
    fun insertFact(facts: List<Fact>)

    @Query("SELECT * FROM Facts")
    fun getAllFacts(): List<Fact>

}