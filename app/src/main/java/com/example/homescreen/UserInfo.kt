package com.example.homescreen

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("userId") val userId: String?,
    @SerializedName("lastName") val userLastName: String?,
    @SerializedName("firstName") val userFirstName: String?,
    @SerializedName("email") val userEmail: String?,
    @SerializedName("amka") val userAmka: String?,
    @SerializedName("password") val userPassword: String?,
    @SerializedName("doctor") val userDoctor: String?,
    @SerializedName("address") val userAddress: String?,
    @SerializedName("blood") val userBloodType: String?
)
