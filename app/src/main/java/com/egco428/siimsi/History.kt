package com.egco428.siimsi

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class History : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history)

        val homeBt = findViewById<ImageView>(R.id.homebt)
        homeBt.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        val startBt = findViewById<ImageView>(R.id.startBt)
        startBt.setOnClickListener {
            val intent = Intent(this,Shekesiimsi::class.java)
            startActivity(intent)
        }
    }
}