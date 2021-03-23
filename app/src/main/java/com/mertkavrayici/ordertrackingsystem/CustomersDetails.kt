package com.mertkavrayici.ordertrackingsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.google.type.LatLng
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.android.synthetic.main.activity_customers_details.*
var name2=""
class CustomersDetails : AppCompatActivity() , OnMapReadyCallback{

    var choosenCustomer=""
    private lateinit var  mMAP:GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customers_details)
        supportActionBar!!.setTitle("Müşteri Detayları")

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val intent=intent
        choosenCustomer= intent.getStringExtra("Name").toString()



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_details,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.nav_orders->{

                val intent= Intent(this,CustomerOrder::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onMapReady(p0: GoogleMap?) {

mMAP=p0!!
        val query=ParseQuery<ParseObject>("Customers")
        query.whereEqualTo("Name",choosenCustomer)
        query.findInBackground { objects, e ->

            if(e!=null){

                Toast.makeText(applicationContext,e.localizedMessage.toString(),Toast.LENGTH_LONG).show()


            }
            else{

                if(objects.size>0){

                    for(parseObject in objects){

                         name2=parseObject.get("Name") as String
                        val latitude=parseObject.get("latitude") as String
                        val longitude=parseObject.get("longitude") as String
                        val official=parseObject.get("Official") as String
                        val offPhone=parseObject.get("OffPhone") as String
                        val address=parseObject.get("Address") as String

                        textView3.text="Şirket Adı :" + name2
                        textView4.text="Şirket Yetkilisi: "+official
                        textView6.text="E-Posta:"+offPhone
                        textView7.text="Şirket Adresi:"+ address

                        val latitudeDouble=latitude.toDouble()
                        val longitudeDouble=longitude.toDouble()
                        val choosenPlace=com.google.android.gms.maps.model.LatLng(latitudeDouble,longitudeDouble)
                        mMAP.addMarker(MarkerOptions().position(choosenPlace).title(name2))
                        mMAP.moveCamera(CameraUpdateFactory.newLatLngZoom(choosenPlace,17f))


                    }

                }

            }
        }


    }
}