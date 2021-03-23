package com.mertkavrayici.ordertrackingsystem

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.android.synthetic.main.activity_products_details.*

class ProductsDetails : AppCompatActivity() {
    var chosenProduct = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_details)
        supportActionBar!!.setTitle("Ürün Detayları")

        val intent = intent
        chosenProduct = intent.getStringExtra("Name").toString()

        val query = ParseQuery<ParseObject>("Products")
        query.whereEqualTo("Name", chosenProduct)
        query.findInBackground { objects, e ->

            if (e != null) {

                Toast.makeText(applicationContext, e.localizedMessage.toString(), Toast.LENGTH_LONG)
                    .show()


            } else {


                if(objects.size>0){


                    for(parseObject in objects){

                        val image=parseObject.get("Image") as ParseFile
                        image.getDataInBackground{data, e ->

                            if(e!=null){

                                Toast.makeText(applicationContext,e.localizedMessage,Toast.LENGTH_LONG).show()
                            }
                            else{
                                val bitmap=BitmapFactory.decodeByteArray(data,0,data.size)
                                imageView4.setImageBitmap(bitmap)
                                val name=parseObject.get("Name") as String
                                val number=parseObject.get("Number") as String
                                nameText.text="Ürün Adı :"+name
                                NumbText.text="Ürün Adeti :"+number

                            }
                        }
                    }
                }

            }

        }

    }
}