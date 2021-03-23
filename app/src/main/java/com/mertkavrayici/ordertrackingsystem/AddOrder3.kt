package com.mertkavrayici.ordertrackingsystem

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.parse.ParseObject
import kotlinx.android.synthetic.main.activity_add_order3.*
import kotlinx.android.synthetic.main.activity_order_product_price.*
import java.util.*

class AddOrder3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_order3)

        supportActionBar!!.setTitle("Sipariş Oluştur")
        cnameText.text = items
        pnameText.text = items1
        priceText.text=items5

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        button.setOnClickListener {

            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->

                    val mmMonth=mMonth+1
                    deliveryText.setText("" + mDay + "/" + mmMonth + "" + "/" + mYear)

                },
                year,
                month,
                day
            )
            dpd.show()

        }

    }

    fun add(view: View) {


        saveToParse()
        sendMail()

    }

    fun saveToParse() {


        val parseObj = ParseObject("Orders")




            parseObj.put("Company", cnameText.text.toString())
            parseObj.put("Product", pnameText.text.toString())
            parseObj.put("OrderName", orderNameText.text.toString())
            parseObj.put("Status", deliveryText.text.toString())

            parseObj.put("TotalPrice",priceText.text.toString())

            parseObj.saveInBackground { e ->

                if (e != null) {

                    Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_LONG).show()
                } else {

                    Toast.makeText(applicationContext, "Sipariş Oluşturuldu", Toast.LENGTH_LONG)
                        .show()
                    val intent = Intent(applicationContext, Orders::class.java)
                    startActivity(intent)
                    finish()
                }
            }

    }
    fun sendMail(){
        val mail= globalMail
        val header="Sipariş Bilgisi"
        val message="Siparişiniz Başarıyla Elimize Ulaşmıştır.En yakın zamanda teslim edilecektir.Teşekkürler."

        val mIntent=Intent(Intent.ACTION_SEND)
        mIntent.data= Uri.parse("mailto:")
        mIntent.type="text/plain"
        mIntent.putExtra(Intent.EXTRA_EMAIL,arrayOf(mail))
        mIntent.putExtra(Intent.EXTRA_SUBJECT,header)
        mIntent.putExtra(Intent.EXTRA_TEXT,message)


        try{


            startActivity(Intent.createChooser(mIntent,"Choose Email Client"))

        }catch(e:Exception){


            Toast.makeText(this,e.message,Toast.LENGTH_LONG).show()
        }


    }
}