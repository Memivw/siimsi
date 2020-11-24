package com.egco428.siimsi

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

class Howto: AppCompatActivity()  {
    private lateinit var actionBar: ActionBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.howto)

        actionBar = this.supportActionBar!!
        actionBar.title ="HowtoSiimsi"
        val homeBt = findViewById<ImageView>(R.id.homebt)
        homeBt.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}