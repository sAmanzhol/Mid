package com.example.mid.api

import com.example.mid.db.entities.Fact
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

internal interface JsonPlaceHolderApi {
    @GET("facts/random?animal_type=cat&amount=2")
    fun getFacts(): Deferred<List<Fact>>

}
