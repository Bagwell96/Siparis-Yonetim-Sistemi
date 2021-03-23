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
import kotlinx.android.synthetic.main.activity_customers.*
import kotlinx.android.synthetic.main.activity_orders.*
import kotlinx.android.synthetic.main.activity_products.*
import kotlinx.android.synthetic.main.activity_products.listView

class Orders : AppCompatActivity() {

    val orderNameList=ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)
        getDataParse()
        supportActionBar!!.setTitle("Siparişler")




        listView.setOnItemClickListener { adapterView, view, i, l ->

            val intent=Intent(applicationContext,OrdersDetails::class.java)
            intent.putExtra("OrderName",orderNameList[i])
            startActivity(intent)
        }


    }



    fun getDataParse(){
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

                    Toast.makeText(applicationContext,"Sipariş Bulunamadı",Toast.LENGTH_LONG).show()
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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater=getMenuInflater()
        menuInflater.inflate(R.menu.add_customer,menu)


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId==R.id.add_new){


            val intent= Intent(this,AddOrders::class.java)
            intent.putExtra("info","new")
            startActivity(intent)



        }


        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {

        menuInflater.inflate(R.menu.customers_popup,menu)

        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {


        return when (item.itemId) {

            R.id.delete -> {
                listView.setOnItemClickListener { adapterView, view, i, l ->




                }


                return true
            }
            R.id.edit -> {


                return true
            }


            else -> return super.onContextItemSelected(item)
        }
    }
}