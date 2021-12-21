package com.example.homescreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homescreen.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginbtn.setOnClickListener{ login() }
        binding.signupbtn.setOnClickListener{ signup() }
    }

    private fun login() {
        val username = binding.username.text.toString()
        val password = binding.password.text.toString()

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill both fields", Toast.LENGTH_LONG).show()
        }
        else if (username.length != 11) {
            Toast.makeText(this, "AMKA must be 11 digits", Toast.LENGTH_LONG).show()
        }
        else {
            val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(getString(R.string.BASE_URL))
                .build()
                .create(RestApi::class.java)
            val userInfo = UserInfo(
                userId = null,
                userLastName = null,
                userFirstName = null,
                userEmail = null,
                userAmka = username,
                userPassword = password,
                userDoctor = null,
                userAddress = null,
                userBloodType = null
            )
            val retrofitData = retrofitBuilder.loginUser(userInfo)
            retrofitData.enqueue(object : Callback<ResponseMessage?> {
                override fun onResponse(call: Call<ResponseMessage?>, response: Response<ResponseMessage?>) {
                    when (response.code()) {
                        200 -> {
                            val jwtoken = response.body()?.jwt
                            val userId = response.body()?.userId
                            val firstName = response.body()?.userFirstName
                            val lastName = response.body()?.userLastName
                            val email = response.body()?.userEmail
                            val amka = response.body()?.userAmka
                            val doctor = response.body()?.userDoctor
                            val address = response.body()?.userAddress
                            val blood = response.body()?.userBloodType
                            Toast.makeText(this@Login, "Connected successfully", Toast.LENGTH_LONG).show()
                            binding.username.text = null
                            binding.password.text = null
                            val intent = Intent(this@Login, MainActivity::class.java)
                            intent.putExtra("jwtoken", jwtoken)
                            intent.putExtra("userId", userId)
                            intent.putExtra("firstName", firstName)
                            intent.putExtra("lastName", lastName)
                            intent.putExtra("email", email)
                            intent.putExtra("amka", amka)
                            intent.putExtra("doctor", doctor)
                            intent.putExtra("address", address)
                            intent.putExtra("blood", blood)
                            startActivity(intent)
                        }
                        401 -> {
                            Toast.makeText(this@Login, "Wrong credentials", Toast.LENGTH_LONG).show()
                            binding.username.text = null
                            binding.password.text = null
                        }
                    }
                }
                override fun onFailure(call: Call<ResponseMessage?>, t: Throwable) {
                    Log.d("Login", "onFailure: " + t.message)
                }
            })
        }
    }

    private fun signup() {
        binding.username.text = null
        binding.password.text = null
        val intent = Intent(this, Signup::class.java)
        startActivity(intent)
    }
}
