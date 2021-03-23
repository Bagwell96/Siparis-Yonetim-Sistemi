package com.mertkavrayici.ordertrackingsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_add_customers.*

var globalName=""
var globalOff=""
var globalMail=""
var globalAdd=""


class AddCustomers : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_customers)
        supportActionBar!!.setTitle("Şirket Bilgileri")



val intent=intent
val info=intent.getStringExtra("info")
if(info.equals("new")){

    addCompNameText.setText("")
    addCompOffText.setText("")
    addOffMailText.setText("")

}
        else{

add_button.visibility=View.INVISIBLE



}



}


fun next(view:View) {

    globalName=addCompNameText.text.toString()
    globalOff=addCompOffText.text.toString()
    globalMail=addOffMailText.text.toString()
    globalAdd=addCompAddText.text.toString()

    val intent=Intent(applicationContext,AddCustomers2::class.java)
    startActivity(intent)
finish()







}


}