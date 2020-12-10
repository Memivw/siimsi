package com.egco428.siimsi

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.android.synthetic.main.howto.*

class Howto: AppCompatActivity()  {
    private lateinit var actionBar: ActionBar
    private var  imwordList = mutableListOf<Int>()
    private var imageList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.howto)

//        set actionbar
        actionBar = this.supportActionBar!!
        actionBar.title ="HowtoSiimsi"

//      Create pager
        postToList()
        view_pager.adapter = ViewPagerAdapter(imwordList,imageList)
        view_pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

//        set dot at page
        val dot = findViewById<WormDotsIndicator>(R.id.dot)
        dot.setViewPager2(view_pager)

//        set Button for intent
        val backBt = findViewById<ImageView>(R.id.backbtn)
        backBt.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

//  function add list to  Model
    private fun addToList(imword:Int,image:Int){
        imwordList.add(imword)
        imageList.add(image)
    }
    private fun postToList(){
        addToList(R.drawable.how_w12,R.drawable.how1)
        addToList(R.drawable.how_w2,R.drawable.how2)
        addToList(R.drawable.how_w3,R.drawable.how3)
        addToList(R.drawable.how_w4,R.drawable.how42)
        addToList(R.drawable.how_w5,R.drawable.how1)
    }
}

