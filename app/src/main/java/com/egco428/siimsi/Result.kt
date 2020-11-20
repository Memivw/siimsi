package com.egco428.siimsi

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.result.*

class Result : AppCompatActivity() {
    private lateinit var screen: View
    private lateinit var imageScreen: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result)

        val paper = AnimationUtils.loadAnimation(this, R.anim.paper)
        val lamp = AnimationUtils.loadAnimation(this, R.anim.ttb)
        val saveAn = AnimationUtils.loadAnimation(this, R.anim.save)
        val backAn = AnimationUtils.loadAnimation(this, R.anim.back)
        paperIm.startAnimation(paper)
        lampIm1.startAnimation(lamp)
        lampIm2.startAnimation(lamp)
        savebt.startAnimation(saveAn)
        backbt.startAnimation(backAn)
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
}