package com.example.homescreen

import retrofit2.Call
import retrofit2.http.*

interface RestApi {

    @Headers("Content-Type: application/json")
    @POST("users/signup")
    fun addUser(@Body userData: UserInfo): Call<ResponseMessage>

    @Headers("Content-Type: application/json")
    @POST("users/login")
    fun loginUser(@Body userData: UserInfo): Call<ResponseMessage>

    @GET("diagnoses")
    fun allDiagnoses(@Header("Authorization") token: String, @Header("userId") userId: String?): Call<List<DiagnosesResponse>>

    @GET("prescriptions")
    fun allPrescriptions(@Header("Authorization") token: String, @Header("userId") userId: String?): Call<List<PrescriptionsResponse>>
}