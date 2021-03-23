package com.mertkavrayici.ordertrackingsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.android.synthetic.main.activity_products.*

class Reports : AppCompatActivity() {

    val orderNameList=ArrayList<String>()
    val reportNameList=ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reports)

        supportActionBar!!.setTitle("Raporlar")

        listView.setOnItemClickListener { adapterView, view, i, l ->

            val intent=Intent(applicationContext,ReportsDetails::class.java)
            intent.putExtra("OrderName",orderNameList[i])
            startActivity(intent)
        }


getDataParse()



    }

    fun getDataParse()
    {

        val search=findViewById<SearchView>(R.id.searchView)
        val listView=findViewById<ListView>(R.id.listView)

        val arrayAdapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,orderNameList)
        listView.adapter=arrayAdapter
        search.setOnQueryTextListener(object :SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                search.clearFocus()
                if(orderNameList.contains(p0)){

                    arrayAdapter.filter.filter(p0)
                }
                else{

                    Toast.makeText(applicationContext,"Rapor Bulunamadı",Toast.LENGTH_LONG).show()
                }

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                arrayAdapter.filter.filter(p0)
                return false
            }

        })

        val query= ParseQuery.getQuery<ParseObject>("Reports")
        query.findInBackground{objects, e ->

            if(e!=null){

                Toast.makeText(applicationContext,e.localizedMessage.toString(), Toast.LENGTH_LONG).show()

            }
            else{

                if(objects.size>0){

                    orderNameList.clear()

                    for(parseObject  in objects){

                        val name=parseObject.get("OrderName") as String
                        orderNameList.add(name)


                    }
                    arrayAdapter.notifyDataSetChanged()


                }

            }

        }




    }
    }
