package com.egco428.siimsi

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.help.*

class HelpActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.help)

        val wordAn = AnimationUtils.loadAnimation(this, R.anim.helpword)
        val findtempbtAn = AnimationUtils.loadAnimation(this, R.anim.find)
        val feedfishbtAn = AnimationUtils.loadAnimation(this, R.anim.feed)
        val backhomebtAn = AnimationUtils.loadAnimation(this, R.anim.backhome)

        helpbanner.startAnimation(wordAn)
        findtempbtn.startAnimation(findtempbtAn)
        feedfishbtn.startAnimation(feedfishbtAn)
        backhomebtn.startAnimation(backhomebtAn)

        val findtempBtn = findViewById<ImageView>(R.id.findtempbtn)
        findtempBtn.setOnClickListener {
            val intent = Intent(this,MapsActivity::class.java)
            startActivity(intent)
        }
        val feedfishBtn = findViewById<ImageView>(R.id.feedfishbtn)
        feedfishBtn.setOnClickListener {
            val intent = Intent(this,FeedFishActivity::class.java)
            startActivity(intent)
        }
        val backhomeBtn = findViewById<ImageView>(R.id.backhomebtn)
        backhomeBtn.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}