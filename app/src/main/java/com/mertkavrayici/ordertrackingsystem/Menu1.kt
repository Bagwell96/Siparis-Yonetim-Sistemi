package com.mertkavrayici.ordertrackingsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.firebase.auth.FirebaseAuth
import com.parse.ParseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_menu1.*

class Menu1 : AppCompatActivity() {






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu1)


        customers.setOnClickListener {
            val intent=Intent(applicationContext,Customers::class.java)
            startActivity(intent)
        }
        products.setOnClickListener {
            val intent=Intent(applicationContext,Products::class.java)
            startActivity(intent)
        }
        orders.setOnClickListener {
            val intent=Intent(applicationContext,Orders::class.java)
            startActivity(intent)
        }
        reports.setOnClickListener {
            val intent=Intent(applicationContext,Reports::class.java)
            startActivity(intent)
        }
        logout.setOnClickListener{

            val intent=Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }
        feedback.setOnClickListener {
            val intent=Intent(applicationContext,Chat::class.java)
            startActivity(intent)
        }






        val intent= intent
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val userName=intent.getStringExtra("username")




    }








}