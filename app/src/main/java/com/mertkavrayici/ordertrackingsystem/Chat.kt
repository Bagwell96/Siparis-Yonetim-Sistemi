package com.mertkavrayici.ordertrackingsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_main.*

class Chat : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        auth=FirebaseAuth.getInstance()

    }

   fun chat(view:View) {


        val mail = chatmail.text.toString()
        val pass = chatpass.text.toString()


       auth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener {task ->

           if(task.isSuccessful){

               val intent = Intent(this, Menu1::class.java)
               startActivity(intent)


           }



        }.addOnFailureListener { exception ->
           Toast.makeText(applicationContext,exception.localizedMessage.toString(),Toast.LENGTH_LONG).show()
       }
    }
}