package com.egco428.siimsi

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.result.*

class Result : AppCompatActivity() {
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
        val saveBt = findViewById<ImageView>(R.id.backbt)
//        backBt.setOnClickListener {
//            val intent = Intent(this,MainActivity::class.java)
//            startActivity(intent)
//        }
    }
}