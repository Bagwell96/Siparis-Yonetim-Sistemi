package com.mertkavrayici.ordertrackingsystem

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.android.synthetic.main.activity_customers.*
import kotlinx.android.synthetic.main.activity_customers.listView
import kotlinx.android.synthetic.main.activity_products.*

var items1=""
class AddOrder2 : AppCompatActivity() ,AdapterView.OnItemClickListener,AdapterView.OnItemSelectedListener{

    val pronameList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_order2)
        supportActionBar!!.setTitle("Sipariş Oluştur")
        val arrayAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_single_choice,pronameList)
        listView.adapter=arrayAdapter
        listView?.choiceMode=ListView.CHOICE_MODE_SINGLE
        listView.onItemClickListener=this

        val query= ParseQuery.getQuery<ParseObject>("Products")
        query.findInBackground{objects, e ->

            if(e!=null){

                Toast.makeText(applicationContext,e.localizedMessage.toString(), Toast.LENGTH_LONG).show()

            }
            else{

                if(objects.size>0){

                    pronameList.clear()

                    for(parseObject  in objects){

                        val name=parseObject.get("Name") as String
                        pronameList.add(name)


                    }
                    arrayAdapter.notifyDataSetChanged()


                }

            }

        }



        }


    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        items1=parent?.getItemAtPosition(position) as String
    }
    fun accept(view:View){

        val intent = Intent(applicationContext, OrderProductPrice::class.java)

        startActivity(intent)
        finish()


    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }
}