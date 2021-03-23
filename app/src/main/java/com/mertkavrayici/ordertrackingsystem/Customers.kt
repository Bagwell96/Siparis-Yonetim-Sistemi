package com.mertkavrayici.ordertrackingsystem

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.android.synthetic.main.activity_customers.*
import kotlinx.android.synthetic.main.activity_menu1.*
import java.lang.Exception





class Customers : AppCompatActivity()

{   var compnameList=ArrayList<String>()
    lateinit var  toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customers)
        supportActionBar!!.setTitle("Müşteriler")




        getDataParse()



        listView.setOnItemClickListener { adapterView, view, i, l ->

            val intent=Intent(applicationContext,CustomersDetails::class.java)
            intent.putExtra("Name",compnameList[i])
            startActivity(intent)
        }









    }


    fun getDataParse(){

        val search=findViewById<SearchView>(R.id.searchView)
        val listView=findViewById<ListView>(R.id.listView)

        val arrayAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,compnameList)
        listView.adapter=arrayAdapter
        registerForContextMenu(listView)


        search.setOnQueryTextListener(object :SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                search.clearFocus()
                if(compnameList.contains(p0)){

                    arrayAdapter.filter.filter(p0)
                }
                else{

                    Toast.makeText(applicationContext,"Müşteri Bulunamadı",Toast.LENGTH_LONG).show()
                }

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                arrayAdapter.filter.filter(p0)
                return false
            }

        })


        val query=ParseQuery.getQuery<ParseObject>("Customers")
        query.findInBackground{objects, e ->

            if(e!=null){

                Toast.makeText(applicationContext,e.localizedMessage.toString(),Toast.LENGTH_LONG).show()

            }
            else{

                if(objects.size>0){

                    compnameList.clear()

                    for(parseObject  in objects){

                        val name=parseObject.get("Name") as String
                        compnameList.add(name)


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


            val intent= Intent(this,AddCustomers::class.java)
            intent.putExtra("info","new")
            startActivity(intent)



        }


        return super.onOptionsItemSelected(item)
    }
   }