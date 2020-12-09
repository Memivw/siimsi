package com.egco428.siimsi

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.end.*

class EndActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.end)

        val wordAn = AnimationUtils.loadAnimation(this, R.anim.shakeword)
        val nextbtnAn = AnimationUtils.loadAnimation(this, R.anim.siimsi)
        endimg.startAnimation(wordAn)
        nextbtn.startAnimation(wordAn)

        val nextBt = findViewById<ImageView>(R.id.nextbtn)
        nextBt.setOnClickListener {
            val intent = Intent(this,HelpActivity::class.java)
            startActivity(intent)
        }
    }
}