package com.example.mid.api

import com.example.mid.model.Dragon
import retrofit2.Call
import retrofit2.http.GET

internal interface JsonPlaceHolderApi {
    @GET("dragons")
    fun getDragons(): Call<List<Dragon>>

}
