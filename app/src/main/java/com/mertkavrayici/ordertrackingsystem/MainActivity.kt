package com.mertkavrayici.ordertrackingsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.parse.LogInCallback
import com.parse.ParseAnalytics
import com.parse.ParseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ParseAnalytics.trackAppOpenedInBackground(intent)



    }




        fun login(view: View) {


            ParseUser.logInInBackground(nameText.text.toString(),passText.text.toString(),
                LogInCallback { user, e ->

                    if(e!=null){

                        Toast.makeText(applicationContext,e.localizedMessage.toString(),Toast.LENGTH_LONG).show()

                    }else{

                        Toast.makeText(applicationContext,"Hoşgeldin : "+user.username.toString(),Toast.LENGTH_LONG).show()

                        val intent= Intent(applicationContext,Menu1::class.java)
                        startActivity(intent)
                        finish()
                    }


                })



            }




                }








