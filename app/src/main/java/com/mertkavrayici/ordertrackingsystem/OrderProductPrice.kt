package com.mertkavrayici.ordertrackingsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.SeekBar
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_order_product_price.*

var items3="10"
var items4="50"
var items5=""
class OrderProductPrice : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_product_price)
        supportActionBar!!.setTitle("Sipariş Oluştur")

        proname.text= "Ürün Adı : "+items1

       // items3=pronumber.text.toString() // Edittext
       // items4=proprice.text.toString()// Edittext


        pronumber.addTextChangedListener ( object:TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
              val first=pronumber.text.toString()
              val second=proprice.text.toString()
                totalprice.text=if(first.isBlank() || second.isBlank()){

                    "Toplam Tutar : 0 TL"

                }else{

                    "Toplam Tutar : ${first.toInt().times(second.toInt())} TL"
                }

            }


        } )

        proprice.addTextChangedListener ( object:TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val first=pronumber.text.toString()

                val second=proprice.text.toString()
                items5=totalprice.text.toString()
                totalprice.text=if(first.isBlank() || second.isBlank()){

                    "Toplam Tutar : 0 TL"

                }else{

                    "Toplam Tutar : ${first.toInt().times(second.toInt())} TL"
                }

            }


        } )











        }

    fun next1(view: View){



        val intent = Intent(applicationContext, AddOrder3::class.java)
        startActivity(intent)
        finish()


    }


    }
