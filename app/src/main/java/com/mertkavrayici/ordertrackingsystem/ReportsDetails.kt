package com.mertkavrayici.ordertrackingsystem

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.common.wrappers.PackageManagerWrapper
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.android.synthetic.main.activity_orders_details.*
import kotlinx.android.synthetic.main.activity_reports_details.*
import java.util.jar.Manifest

class ReportsDetails : AppCompatActivity() {

    private val STORAGE_CODE: Int=500
    var chosenReport=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reports_details)
        supportActionBar!!.setTitle("Rapor Detayları")

    val intent=intent
        chosenReport=intent.getStringExtra("OrderName").toString()

        val query = ParseQuery<ParseObject>("Reports")
        query.whereEqualTo("OrderName", chosenReport)

        query.findInBackground { objects, e ->

            if (e != null) {

                Toast.makeText(applicationContext, e.localizedMessage.toString(), Toast.LENGTH_LONG)
                    .show()


            }
            else{
                if(objects.size>0){


                    for (parseObject in objects){


                        val orderName=parseObject.get("OrderName") as String
                        val custName=parseObject.get("Company") as String
                        val prodName=parseObject.get("Product") as String
                        val status=parseObject.get("Status") as String
                        val price=parseObject.get("TotalPrice") as String

                        textView12.text=orderName
                        textView13.text=custName
                        textView14.text=prodName
                        textView15.text=status
                        textView.text=price




                    }
                }

            }

        }

    }



    fun pdf(view: View){

if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M){

if(checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){

    val permissions= arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    requestPermissions(permissions,STORAGE_CODE)

}
    else
{

savePdf()
}

}
else{

    savePdf()
}




    }
    private fun savePdf(){}


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){

            STORAGE_CODE->{

                if(grantResults.size>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){


                }
                else{

                    Toast.makeText(this,"İzin Verilmedi!",Toast.LENGTH_LONG).show()

                }

            }
        }
    }

}