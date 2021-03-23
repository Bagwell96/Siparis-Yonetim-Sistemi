package com.mertkavrayici.ordertrackingsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.android.synthetic.main.activity_products.*

class CustomerOrder : AppCompatActivity() {
    val comporderList=ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)
        getDataParse()
        supportActionBar!!.setTitle("Siparişler")




        listView.setOnItemClickListener { adapterView, view, i, l ->

            val intent= Intent(applicationContext,OrdersDetails::class.java)
            intent.putExtra("OrderName",comporderList[i])
            startActivity(intent)
        }


    }



    fun getDataParse(){
        val search=findViewById<SearchView>(R.id.searchView)
        val listView=findViewById<ListView>(R.id.listView)

        val arrayAdapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,comporderList)
        listView.adapter=arrayAdapter

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                search.clearFocus()
                if(comporderList.contains(p0)){

                    arrayAdapter.filter.filter(p0)
                }
                else{

                    Toast.makeText(applicationContext,"Sipariş Bulunamadı", Toast.LENGTH_LONG).show()
                }

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                arrayAdapter.filter.filter(p0)
                return false
            }

        })

        val query= ParseQuery.getQuery<ParseObject>("Orders")
        query.findInBackground{objects, e ->

            if(e!=null){

                Toast.makeText(applicationContext,e.localizedMessage.toString(), Toast.LENGTH_LONG).show()

            }
            else{

                if(objects.size>0){
                    comporderList.clear()

                    for(parseObject  in objects){

                        val name=parseObject.get("Company") as String
                        if(name.equals(name2.toString())){
                            comporderList.add(name)
                        }



                    }
                    arrayAdapter.notifyDataSetChanged()


                }

            }

        }

    }






        }

