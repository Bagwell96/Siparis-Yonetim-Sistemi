package com.mertkavrayici.ordertrackingsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.android.synthetic.main.activity_add_order3.*
import kotlinx.android.synthetic.main.activity_orders_details.*
var orderName=""
var items2=""
class OrdersDetails : AppCompatActivity(),View.OnClickListener{
    var chosenOrder=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders_details)
        supportActionBar!!.setTitle("Sipariş Detayları")

        checkBox.setOnClickListener(this)
        checkBox2.setOnClickListener(this)





        val intent = intent
        chosenOrder = intent.getStringExtra("OrderName").toString()
    val query = ParseQuery<ParseObject>("Orders")
    query.whereEqualTo("OrderName", chosenOrder )

    query.findInBackground { objects, e ->

        if (e != null) {

            Toast.makeText(applicationContext, e.localizedMessage.toString(), Toast.LENGTH_LONG)
                .show()


        }
        else{
            if(objects.size>0){


                for (parseObject in objects){


                     orderName=parseObject.get("OrderName") as String
                    val custName=parseObject.get("Company") as String
                    val prodName=parseObject.get("Product") as String
                    val status=parseObject.get("Status") as String
                    val total=parseObject.get("TotalPrice") as String


                    textView8.text="Sipariş Adı :"+orderName
                    textView9.text="Müşteri Adı :"+custName
                    textView10.text="Ürün Adı :"+prodName
                    textView11.text= "Teslimat Tarihi :"+status
                    textView18.text="Toplam Tutar :"+total +" TL"





                }
            }

        }

    }



}










    override fun onClick(p0: View?) {
        p0 as CheckBox
        var isChecked=p0.isChecked

        when(p0.id){

            R.id.checkBox ->if(isChecked){

               items2=checkBox.text.toString()
                checkBox2.isChecked=false
            }

            R.id.checkBox2->if(isChecked){

                items2=checkBox2.text.toString()
                checkBox.isChecked=false
            }


        }
    }

    fun addReport(view:View){

        if(items2.equals("Hazırlanıyor")){

            val intent= Intent(applicationContext,Orders::class.java)
            startActivity(intent)




        }

        else if(items2.equals("Teslim Edildi")){

            val parseObj5 = ParseObject("Reports")
            parseObj5.put("Company", textView9.text.toString())
            parseObj5.put("Product", textView10.text.toString())
            parseObj5.put("OrderName", textView8.text.toString())
            parseObj5.put("Status", textView11.text.toString())
            parseObj5.put("TotalPrice",textView18.text.toString())


            parseObj5.saveInBackground { e ->

                if (e != null) {

                    Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_LONG).show()
                } else {

                    Toast.makeText(applicationContext, "Rapor Oluşturuldu", Toast.LENGTH_LONG)
                        .show()
                    val intent = Intent(applicationContext, Orders::class.java)
                    startActivity(intent)
                }
            }





        }








    }




}