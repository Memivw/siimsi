package com.egco428.siimsi

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        val ttb = AnimationUtils.loadAnimation(this, R.anim.ttb)
        val nameAn = AnimationUtils.loadAnimation(this, R.anim.name)
        val fadein = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val siimsi = AnimationUtils.loadAnimation(this, R.anim.siimsi)
        val siimsibounce = AnimationUtils.loadAnimation(this, R.anim.bounce)
        val startAn = AnimationUtils.loadAnimation(this, R.anim.startbt)
        circleimageView.startAnimation(fadein)
        name.startAnimation(nameAn)
//        siimsiIm.startAnimation(siimsi)
//        startbt.startAnimation(ttb)
        val histBt = findViewById<ImageView>(R.id.historybt)
        histBt.setOnClickListener {
            val intent = Intent(this,History::class.java)
            startActivity(intent)
        }
        val howtoBt = findViewById<ImageView>(R.id.howtobt)
        howtoBt.setOnClickListener {
            val intent = Intent(this,Howto::class.java)
            startActivity(intent)
        }
//
        val startBt = findViewById<ImageView>(R.id.startbt)
        startBt.setOnClickListener {
            val intent = Intent(this,Shekesiimsi::class.java)
            startActivity(intent)
        }
    }
}