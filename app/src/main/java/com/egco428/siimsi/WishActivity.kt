package com.egco428.siimsi

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.end.*

class WishActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wish)

//        val wordAn = AnimationUtils.loadAnimation(this, R.anim.shakeword)
//        val nextbtnAn = AnimationUtils.loadAnimation(this, R.anim.siimsi)
//        endimg.startAnimation(wordAn)
//        nextbtn.startAnimation(wordAn)
//
        val backBt = findViewById<ImageView>(R.id.backhomebtn)
        backBt.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}