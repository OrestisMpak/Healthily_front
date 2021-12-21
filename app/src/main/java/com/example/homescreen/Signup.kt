package com.example.homescreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.homescreen.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Signup : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.click.setOnClickListener{ click() }
        binding.btnsignup.setOnClickListener{ validate() }

        val actionBar = supportActionBar
        actionBar!!.title = "Sign Up"
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    private fun click() {
        binding.LastName.text = null
        binding.FirstName.text = null
        binding.email.text = null
        binding.AMKA.text = null
        binding.inputPassword.text = null
        binding.inputConfirmPassword.text = null
        binding.doctor.text = null
        binding.address.text = null
        binding.bloodType.text = null
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }

    private fun validate() {
        val lastName = binding.LastName.text.toString()
        val firstName = binding.FirstName.text.toString()
        val email = binding.email.text.toString()
        val amka = binding.AMKA.text.toString()
        val password = binding.inputPassword.text.toString()
        val confirmPassword = binding.inputConfirmPassword.text.toString()
        val doctor = binding.doctor.text.toString()
        val address = binding.address.text.toString()
        val blood = binding.bloodType.text.toString()

        if (lastName.isEmpty() || firstName.isEmpty() || email.isEmpty()
            || amka.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Fields with * are required", Toast.LENGTH_LONG).show()
        }
        else if (password!=confirmPassword) {
            Toast.makeText(this, "Password does not match", Toast.LENGTH_LONG).show()
            binding.inputPassword.text = null
            binding.inputConfirmPassword.text = null
        }
        else if (amka.length != 11) {
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
                userLastName = lastName,
                userFirstName = firstName,
                userEmail = email,
                userAmka = amka,
                userPassword = password,
                userDoctor = doctor,
                userAddress = address,
                userBloodType = blood
            )
            val retrofitData = retrofitBuilder.addUser(userInfo)
            retrofitData.enqueue(object : Callback<ResponseMessage?> {
                override fun onResponse(call: Call<ResponseMessage?>, response: Response<ResponseMessage?>) {
                    when (response.code()) {
                        201 -> {
                            Toast.makeText(this@Signup, "You have successfully registered", Toast.LENGTH_LONG).show()
                            binding.LastName.text = null
                            binding.FirstName.text = null
                            binding.email.text = null
                            binding.AMKA.text = null
                            binding.inputPassword.text = null
                            binding.inputConfirmPassword.text = null
                            binding.doctor.text = null
                            binding.address.text = null
                            binding.bloodType.text = null
                            val intent = Intent(this@Signup, Login::class.java)
                            startActivity(intent)
                        }
                        409 -> {
                            Toast.makeText(this@Signup, "User already exists", Toast.LENGTH_LONG).show()
                            binding.LastName.text = null
                            binding.FirstName.text = null
                            binding.email.text = null
                            binding.AMKA.text = null
                            binding.inputPassword.text = null
                            binding.inputConfirmPassword.text = null
                            binding.doctor.text = null
                            binding.address.text = null
                            binding.bloodType.text = null
                        }
                        500 -> {
                            Toast.makeText(this@Signup, "Server error", Toast.LENGTH_LONG).show()
                            binding.LastName.text = null
                            binding.FirstName.text = null
                            binding.email.text = null
                            binding.AMKA.text = null
                            binding.inputPassword.text = null
                            binding.inputConfirmPassword.text = null
                            binding.doctor.text = null
                            binding.address.text = null
                            binding.bloodType.text = null
                        }
                    }
                }
                override fun onFailure(call: Call<ResponseMessage?>, t: Throwable) {
                    Log.d("Signup", "onFailure: " + t.message)
                }
            })
        }
    }
}