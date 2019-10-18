package com.example.mid.model

import com.google.gson.annotations.SerializedName

data class Dragon(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("active") val active: Boolean,
    @SerializedName("crew_capacity") val crewCapacity: Int,
    @SerializedName("flickr_images") val flickrImages: List<String>? = null,
    @SerializedName("description") val description: String
)