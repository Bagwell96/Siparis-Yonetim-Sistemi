package com.mertkavrayici.ordertrackingsystem

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.android.synthetic.main.activity_customers.*
import kotlinx.android.synthetic.main.activity_products.*
import kotlinx.android.synthetic.main.activity_products.listView

class Products : AppCompatActivity() {

    val pronameList = ArrayList<String>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        supportActionBar!!.setTitle("Ürünler")

        getDataParse()

        listView.setOnItemClickListener { adapterView, view, i, l ->

            val intent=Intent(applicationContext,ProductsDetails::class.java)
            intent.putExtra("Name",pronameList[i])
            startActivity(intent)
        }







        }
    fun getDataParse(){

        val search=findViewById<SearchView>(R.id.searchView)
        val listView=findViewById<ListView>(R.id.listView)

        val arrayAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,pronameList)
        listView.adapter=arrayAdapter

        search.setOnQueryTextListener(object :SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                search.clearFocus()
                if(pronameList.contains(p0)){

                    arrayAdapter.filter.filter(p0)
                }
                else{

                    Toast.makeText(applicationContext,"Ürün Bulunamadı",Toast.LENGTH_LONG).show()
                }

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                arrayAdapter.filter.filter(p0)
                return false
            }

        })


        val query= ParseQuery.getQuery<ParseObject>("Products")
        query.findInBackground{objects, e ->

            if(e!=null){

                Toast.makeText(applicationContext,e.localizedMessage.toString(),Toast.LENGTH_LONG).show()

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

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {

            val menuInflater = getMenuInflater()
            menuInflater.inflate(R.menu.add_customer, menu)


            return super.onCreateOptionsMenu(menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {

            if (item.itemId == R.id.add_new) {


                val intent = Intent(this, AddProducts::class.java)
                intent.putExtra("info", "new")
                startActivity(intent)


            }


            return super.onOptionsItemSelected(item)
        }
    }
