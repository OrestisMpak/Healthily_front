package com.example.homescreen

import com.google.gson.annotations.SerializedName

data class PrescriptionsResponse(
    @SerializedName("medicine") val userMedicine: String?,
    @SerializedName("doctor") val userDoctor: String?,
    @SerializedName("date") val userDate: String?,
)
