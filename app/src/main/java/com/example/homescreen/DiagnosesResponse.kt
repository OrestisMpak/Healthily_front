package com.example.homescreen

import com.google.gson.annotations.SerializedName

data class DiagnosesResponse(
    @SerializedName("disease") val userDisease: String?,
    @SerializedName("doctor") val userDoctor: String?,
    @SerializedName("date") val userDate: String?,
)
