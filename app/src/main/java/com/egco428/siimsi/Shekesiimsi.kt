package com.egco428.siimsi

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.shakesiimsi.*

class Shekesiimsi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shakesiimsi)

        val ttb = AnimationUtils.loadAnimation(this, R.anim.ttb)
        val siimsi = AnimationUtils.loadAnimation(this, R.anim.siimsi)
        val shakewordAn = AnimationUtils.loadAnimation(this, R.anim.shakeword)
        val siimsishake = AnimationUtils.loadAnimation(this, R.anim.shake)
        wordtv.startAnimation(ttb)
        siimsiIm.startAnimation(siimsi)
        wordshaketv.startAnimation(shakewordAn)
        val shakesiimsi = findViewById<ImageView>(R.id.siimsiIm)
        shakesiimsi.setOnClickListener {
            siimsiIm.startAnimation(siimsishake)
            val intent = Intent(this,Result::class.java)
            startActivity(intent)
        }
    }
}
