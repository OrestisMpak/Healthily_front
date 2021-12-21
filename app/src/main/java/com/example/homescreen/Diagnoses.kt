package com.example.homescreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homescreen.R
import kotlinx.android.synthetic.main.activity_diagnoses.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Diagnoses : AppCompatActivity() {

    //lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnoses)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        val actionBar = supportActionBar
        actionBar!!.title = "Diagnoses"
        actionBar.setDisplayHomeAsUpEnabled(false)

        val jwt = intent.getStringExtra("token")
        val userId = intent.getStringExtra("userId")
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(getString(R.string.BASE_URL))
            .build()
            .create(RestApi::class.java)
        val retrofitData = retrofitBuilder.allDiagnoses("Bearer $jwt", userId)
        retrofitData.enqueue(object : Callback<List<DiagnosesResponse>?> {
            override fun onResponse(call: Call<List<DiagnosesResponse>?>, response: Response<List<DiagnosesResponse>?>) {
                when (response.code()) {
                    200 -> {
                        adapter = RecyclerAdapter(baseContext, response.body()!!)
                        adapter.notifyDataSetChanged()
                        recyclerView.adapter = adapter
                    }
                    404 -> {
                        Toast.makeText(this@Diagnoses, "Diagnoses are empty", Toast.LENGTH_LONG).show()
                    }
                    500 -> {
                        Toast.makeText(this@Diagnoses, "Server error", Toast.LENGTH_LONG).show()
                    }
                }
            }
            override fun onFailure(call: Call<List<DiagnosesResponse>?>, t: Throwable) {
                Log.d("Login", "onFailure: " + t.message)
            }
        })
    }
}