package com.example.homescreen

import com.google.gson.annotations.SerializedName

class ResponseMessage (
    @SerializedName("message") val message: String?,
    @SerializedName("token") val jwt: String?,
    @SerializedName("userId") val userId: String?,
    @SerializedName("lastName") val userLastName: String?,
    @SerializedName("firstName") val userFirstName: String?,
    @SerializedName("email") val userEmail: String?,
    @SerializedName("amka") val userAmka: String?,
    @SerializedName("password") val userPassword: String?,
    @SerializedName("doctor") val userDoctor: String?,
    @SerializedName("address") val userAddress: String?,
    @SerializedName("blood") val userBloodType: String?,
    @SerializedName("disease") val userDisease: String?,
    @SerializedName("date") val userDate: String?,
    @SerializedName("medicine") val userMedicine: String?
)