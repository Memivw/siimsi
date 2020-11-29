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

        actionBar = this.supportActionBar!!
        actionBar.title ="HowtoSiimsi"
//        val homeBt = findViewById<ImageView>(R.id.homebt)
//        homeBt.setOnClickListener {
//            val intent = Intent(this,MainActivity::class.java)
//            startActivity(intent)
//        }
        postToList()
        view_pager.adapter = ViewPagerAdapter(imwordList,imageList)
        view_pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val dot = findViewById<WormDotsIndicator>(R.id.dot)
        dot.setViewPager2(view_pager)
    }
    private fun addToList(imword:Int,image:Int){
        imwordList.add(imword)
        imageList.add(image)
    }
    private fun postToList(){
        addToList(R.drawable.how_w1,R.drawable.how1)
        addToList(R.drawable.how_w2,R.drawable.how2)
        addToList(R.drawable.how_w3,R.drawable.how3)
        addToList(R.drawable.how_w4,R.drawable.how4)
    }
}

