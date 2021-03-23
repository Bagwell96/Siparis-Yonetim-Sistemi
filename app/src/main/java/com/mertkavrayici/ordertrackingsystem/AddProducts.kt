package com.mertkavrayici.ordertrackingsystem

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseUser
import kotlinx.android.synthetic.main.activity_add_customers.*
import kotlinx.android.synthetic.main.activity_add_customers.add_button
import kotlinx.android.synthetic.main.activity_add_products.*
import kotlinx.android.synthetic.main.activity_customers.*
import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.collections.ArrayList

class AddProducts : AppCompatActivity() {
    var selectedPicture : Uri?=null
var selectedBitmap: Bitmap?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_products)
        supportActionBar!!.setTitle("Ürün Bilgileri")






        val intent=intent
        val info=intent.getStringExtra("info")
        if(info.equals("new")){

            addProNameText.setText("")
            addProNumber.setText("")

            add_button2.visibility=View.VISIBLE
            val selectedImageBackground= BitmapFactory.decodeResource(applicationContext.resources,R.drawable.image)
            imageView3.setImageBitmap(selectedImageBackground)


        }

        else{






        }



    }

    fun add(view: View) {

saveToParse()





    }
    fun saveToParse(){

        val byteArrayOutputStream=ByteArrayOutputStream()
        selectedBitmap!!.compress(Bitmap.CompressFormat.PNG,50,byteArrayOutputStream)
        val bytes=byteArrayOutputStream.toByteArray()

        val parseFile=ParseFile("image.png",bytes)

        val parseObj=ParseObject("Products")
        parseObj.put("Name",addProNameText.text.toString())
        parseObj.put("Number",addProNumber.text.toString())
        parseObj.put("Username",ParseUser.getCurrentUser().username.toString())
        parseObj.put("Image",parseFile)

        parseObj.saveInBackground { e->

            if(e!=null){

                Toast.makeText(applicationContext,e.localizedMessage,Toast.LENGTH_LONG).show()
            }
            else{

                Toast.makeText(applicationContext,"Ürün Oluşturuldu",Toast.LENGTH_LONG).show()
                val intent=Intent(applicationContext,Products::class.java)
                startActivity(intent)
                finish()
            }
        }


    }









    fun imageClicked(view: View) {

        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )

        } else {

            val intentToGallery =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intentToGallery, 2)


        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (requestCode == 1) {

            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                val intentToGallery =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intentToGallery, 2)

            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==2 && resultCode== Activity.RESULT_OK && data!=null){

            selectedPicture=data.data
            try {
                if(selectedPicture!=null) {

                    if (Build.VERSION.SDK_INT >= 28) {

                        val source = ImageDecoder.createSource(this.contentResolver, selectedPicture!!)
                        val bitmap = ImageDecoder.decodeBitmap(source)
                        imageView3.setImageBitmap(bitmap)

                    } else {

                        selectedBitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedPicture)
                        imageView3.setImageBitmap(selectedBitmap)
                    }

                }
            }catch (e:Exception){

                e.printStackTrace()
            }



        }

        super.onActivityResult(requestCode, resultCode, data)
    }



}