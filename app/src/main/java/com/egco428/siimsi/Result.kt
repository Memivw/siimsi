package com.egco428.siimsi

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.result.*
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream

class Result : AppCompatActivity() {
    private lateinit var screen: View
    private lateinit var imageScreen: ImageView

    private val REQUEST_CODE = 1
    private var bitmap:Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result)

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
        val backBt = findViewById<ImageView>(R.id.backbt)
        backBt.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        screen = findViewById(R.id.resultscreen)
        imageScreen = findViewById(R.id.imagescreen)
        val saveBt = findViewById<ImageView>(R.id.savebt)
        saveBt.setOnClickListener {
            val b = Screenshot.takeScreenshotOfRootView(imagescreen)
            imagescreen.setImageBitmap(b)
//            screen.setBackgroundColor(Color.parseColor("#999999"))
        }
    }
    companion object Screenshot {
        private fun takeScreenshot(view: View): Bitmap {
            view.isDrawingCacheEnabled = true
            view.buildDrawingCache(true)
            val b = Bitmap.createBitmap(view.drawingCache)
            view.isDrawingCacheEnabled = false
            return b
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
}