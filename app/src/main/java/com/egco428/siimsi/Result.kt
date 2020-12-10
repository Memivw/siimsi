package com.egco428.siimsi

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.Manifest
import android.os.Bundle
import android.util.Log
import android.os.Environment
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import kotlinx.android.synthetic.main.result.*
import kotlinx.android.synthetic.main.result.numbertv
import kotlinx.android.synthetic.main.shakesiimsi.*
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

//        set actionbar
        actionBar = this.supportActionBar!!
        actionBar.title = "prediction"

//        set animation
        val paper = AnimationUtils.loadAnimation(this, R.anim.paper)
        val lamp = AnimationUtils.loadAnimation(this, R.anim.ttb)
        val saveAn = AnimationUtils.loadAnimation(this, R.anim.save)
        val backAn = AnimationUtils.loadAnimation(this, R.anim.back)
        val resultwordAn = AnimationUtils.loadAnimation(this, R.anim.shakeword)
        paperIm.startAnimation(paper)
        lampIm1.startAnimation(lamp)
        lampIm2.startAnimation(lamp)
        savebt.startAnimation(saveAn)
        nextbt.startAnimation(backAn)

 //        get number from Shakesiimsi
        val luckynumber = intent.getStringExtra("luckynumber")

//        set text and animation
        numbertv.text = "ใบที่ " + luckynumber
        numbertv.startAnimation(paper)
        resultmessage.startAnimation(resultwordAn)
        screen = findViewById(R.id.resultscreen)

//        load data from database
        database = FirebaseDatabase.getInstance()
        reference = database.getReference("simsii")
        ShowData()

//        set button for intent
        val backBt = findViewById<ImageView>(R.id.nextbt)
        backBt.setOnClickListener {
            val intent = Intent(this,EndActivity::class.java)
            startActivity(intent)
        }

//        save image to storage and zoom
        val saveBt = findViewById<ImageView>(R.id.savebt)
        saveBt.setOnClickListener {
            val b = Screenshot.takeScreenshotOfRootView(screen)
            imageZoom.setImage(ImageSource.bitmap(b))
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

//    function load and set result
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

//  create image by sceenshot
    private fun CreateImage(): Bitmap? {
        screen = findViewById(R.id.resultscreen)
        val imageZoom = findViewById<SubsamplingScaleImageView>(R.id.imageZoom)
        val image = Screenshot.takeScreenshotOfRootView(screen)
        imageZoom.setImage(ImageSource.bitmap(image))
        return image
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

    //    funtion save Image to storage
    private fun saveImageToExternalStorage(bitmap:Bitmap){
        // Get the external storage directory path
        val path = Environment.getExternalStorageDirectory().toString()

        // Create a file to save the image
        val file = File(path, "siimsi.jpg")

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
            Toast.makeText(this, "can not save image", Toast.LENGTH_SHORT).show()
        }

        // Return the saved image path to uri

    }

    override fun onStop() {
        super.onStop()
    }
}