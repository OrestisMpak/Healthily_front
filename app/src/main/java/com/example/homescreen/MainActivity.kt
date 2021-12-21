package com.example.homescreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Button
//import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homescreen.databinding.ActivityMainBinding
import com.example.homescreen.ui.home.HomeFragment
import com.example.homescreen.ui.logout.LogoutFragment
import kotlinx.android.synthetic.main.card_layout_home.view.*
import kotlinx.android.synthetic.main.fragment_home.*

//import com.example.homescreen.ui.home.HomeFragment
//import com.example.homescreen.ui.home.HomeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerAdapterHome
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        recyclerViewHome.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerViewHome.layoutManager = linearLayoutManager
        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        val name = "$firstName $lastName"
        val amka = intent.getStringExtra("amka")
        val email = intent.getStringExtra("email")
        val address = intent.getStringExtra("address")
        val blood = intent.getStringExtra("blood")

        adapter = RecyclerAdapterHome(baseContext, name, amka, email, address, blood)
        adapter.notifyDataSetChanged()
        recyclerViewHome.adapter=adapter

        /*binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_logout, R.id.nav_changePassword, R.id.nav_deleteAccount, R.id.nav_changeLanguage, R.id.nav_changeTheme, R.id.nav_share
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //val recyclerViewHome = findViewById<RecyclerView>(R.id.recyclerView)
        val jwt = intent.getStringExtra("jwtoken")
        val userId = intent.getStringExtra("userId")
        //Diagnoses Button
        val DiagnosesBtn: Button = findViewById (R.id.DiagnosesBtn)

        DiagnosesBtn.setOnClickListener {
            val intent = Intent(this, Diagnoses::class.java)
            intent.putExtra("token", jwt)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }

        //Prescription Button
        val PrescriptionsBtn: Button = findViewById (R.id.PrescriptionsBtn)

        PrescriptionsBtn.setOnClickListener {
            val intent = Intent(this, Prescriptions::class.java)
            intent.putExtra("token", jwt)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }

        //Appointment Button
        val AppointmentBtn: Button = findViewById (R.id.AppointmentBtn)

        AppointmentBtn.setOnClickListener {
            val intent = Intent(this, Appointment::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}