package com.egco428.siimsi

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.Manifest
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.os.Environment
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import kotlinx.android.synthetic.main.result.*
import java.io.*
import java.util.*


class Result : AppCompatActivity() {
    private lateinit var screen: View
    private lateinit var imageScreen: ImageView
    private lateinit var actionBar: ActionBar
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private val REQUEST_CODE = 1
    private var bitmap:Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result)
        actionBar = this.supportActionBar!!
        actionBar.title = "prediction"
        val luckynumber = intent.getStringExtra("luckynumber")
        val paper = AnimationUtils.loadAnimation(this, R.anim.paper)
        val lamp = AnimationUtils.loadAnimation(this, R.anim.ttb)
        val saveAn = AnimationUtils.loadAnimation(this, R.anim.save)
        val backAn = AnimationUtils.loadAnimation(this, R.anim.back)
        paperIm.startAnimation(paper)
        lampIm1.startAnimation(lamp)
        lampIm2.startAnimation(lamp)
        savebt.startAnimation(saveAn)
        backbt.startAnimation(backAn)
        numbertv.text = "ใบที่ " + luckynumber
        database = FirebaseDatabase.getInstance()
        reference = database.getReference("simsii")
        ShowData()
        val backBt = findViewById<ImageView>(R.id.backbt)
        backBt.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        screen = findViewById(R.id.resultscreen)
        imageScreen = findViewById(R.id.imagescreen)
        val saveBt = findViewById<ImageView>(R.id.savebt)
        saveBt.setOnClickListener {
            val b = Screenshot.takeScreenshotOfRootView(screen)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),100)
                }else{
                    saveImageToExternalStorage(b)
                }
            }else{
                saveImageToExternalStorage(b)
            }

        }
    }
    private fun ShowData(){
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val luckynumber = intent.getStringExtra("luckynumber")
                var text = ArrayList<Simsii>()
                for (data in p0.children){
                    val model = data.getValue(Simsii::class.java)
                    text.add(model as Simsii)
                    if (luckynumber!!.toInt() == model!!.number){
                        resultmessage.text = model.message
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("error",error.toString())
            }
        })
    }
    companion object Screenshot {
        private fun takeScreenshot(view: View): Bitmap {
            view.isDrawingCacheEnabled = true
            view.buildDrawingCache(true)
            val b = Bitmap.createBitmap(view.drawingCache)
            view.isDrawingCacheEnabled = false
            val crop = Bitmap.createBitmap(b,30,230 ,1000,1600)
            return crop
        }
        fun takeScreenshotOfRootView(v: View): Bitmap {
            return takeScreenshot(v.rootView)
        }
    }
    fun onPhotoClicked(view:View){
        val intent = Intent()
        intent.type = "image/*" // image type
        intent.action = Intent.ACTION_GET_CONTENT
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(intent, REQUEST_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var stream: InputStream? = null
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            try{
                if(bitmap!=null){
                    bitmap!!.recycle()
                }
                stream = contentResolver.openInputStream(data!!.data!!)
                bitmap = BitmapFactory.decodeStream(stream)
                imageScreen.setImageBitmap(bitmap)
            }
            catch (e: FileNotFoundException){
                e.printStackTrace()
            }
            finally{
                if(stream!=null){
                    try {
                        stream!!.close()
                    }
                    catch (e: IOException){
                        e.printStackTrace()
                    }

                }
            }
        }

    }
    private fun saveImageToExternalStorage(bitmap:Bitmap){
        // Get the external storage directory path
        val path = Environment.getExternalStorageDirectory().toString()

        // Create a file to save the image
        val file = File(path, "${UUID.randomUUID()}.jpg")

        try {
            // Get the file output stream
            val stream: OutputStream = FileOutputStream(file)

            // Compress the bitmap
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)

            // Flush the output stream
            stream.flush()

            // Close the output stream
            stream.close()
            Toast.makeText(this, "save success", Toast.LENGTH_SHORT).show()
        } catch (e: IOException){ // Catch the exception
            e.printStackTrace()
            Toast.makeText(this, "errorrrrr", Toast.LENGTH_SHORT).show()
        }

        // Return the saved image path to uri

    }
}