package com.example.mid.model

import com.google.gson.annotations.SerializedName

data class Fact(
    @SerializedName("text") val text: String
)