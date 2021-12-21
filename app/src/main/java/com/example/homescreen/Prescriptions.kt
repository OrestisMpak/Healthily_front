package com.example.homescreen

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Prescriptions : AppCompatActivity() {

    //lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var adapter: RecyclerAdapterPrescriptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prescriptions)

        val recyclerViewPr = findViewById<RecyclerView>(R.id.recyclerViewPr)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerViewPr.layoutManager = linearLayoutManager

        val actionBar = supportActionBar
        actionBar!!.title = "Prescriptions"
        actionBar.setDisplayHomeAsUpEnabled(false)

        val jwt = intent.getStringExtra("token")
        val userId = intent.getStringExtra("userId")
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(getString(R.string.BASE_URL))
            .build()
            .create(RestApi::class.java)
        val retrofitData = retrofitBuilder.allPrescriptions("Bearer $jwt", userId)
        retrofitData.enqueue(object : Callback<List<PrescriptionsResponse>?> {
            override fun onResponse(call: Call<List<PrescriptionsResponse>?>, response: Response<List<PrescriptionsResponse>?>) {
                when (response.code()) {
                    200 -> {
                        adapter = RecyclerAdapterPrescriptions(baseContext, response.body()!!)
                        adapter.notifyDataSetChanged()
                        recyclerViewPr.adapter = adapter
                    }
                    404 -> {
                        Toast.makeText(this@Prescriptions, "Prescriptions are empty", Toast.LENGTH_LONG).show()
                    }
                    500 -> {
                        Toast.makeText(this@Prescriptions, "Server error", Toast.LENGTH_LONG).show()
                    }
                }
            }
            override fun onFailure(call: Call<List<PrescriptionsResponse>?>, t: Throwable) {
                Log.d("Login", "onFailure: " + t.message)
            }
        })
    }
}