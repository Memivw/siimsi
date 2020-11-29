package com.egco428.siimsi

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.history.*

class History : AppCompatActivity() {
    private lateinit var actionBar:ActionBar
    private lateinit var myModelList:ArrayList<MyModel>
    private lateinit var myAdapter:MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history)
        //setContentView(ImageViewwithZoom(this))

        val homeBt = findViewById<ImageView>(R.id.homebt)
        homeBt.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        actionBar = this.supportActionBar!!
        loadCards()
        viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                val title = myModelList[position].title
                actionBar.title = title
            }

            override fun onPageSelected(position: Int) {
            }
        })
    }
    private fun loadCards(){
        myModelList = ArrayList()

        myModelList.add(
            MyModel(
                "ประวัติวัดเจดีย์",
                "วัดเจดีย์หรือวัดไอ้ไข่ ตั้งอยู่ที่ หมู่ 7 ตำบลฉลอง อำเภอสิชล จังหวัดนครศรีธรรมราช เคยเป็นวัดร้างที่เชื่อกันว่าสร้างมาเป็นเวลานับ1000 ปี มีเพียงเจดีย์โบราณเก่ารกร้างอยู่ตรงบริเวณที่กำลังสร้างโบสถ์ในปัจจุบัน จนเมื่อประมาณ พ.ศ.2500 มีการบูรณะวัดเจดีย์ขึ้นมาใหม่ เป็นที่ประดิษฐานของ “พ่อท่าน” พระพุทธรูปเก่าแก่ที่อยู่มาตั้งแต่ยังป็นวัดร้าง\n",
                R.drawable.h1)
        )
        myModelList.add(
            MyModel(
                "ประวัติไอ้ไข่",
                "ไอ้ไข่วัดเจดีย์ หรือ ตาไข่วัดเจดีย์ คือรูปไม้แกะสลักของเด็กชายอายุประมาณ 9 -10 ขวบ ตั้งอยู่ในศาลาในวัดเจดีย์เชื่อกันว่าเป็นวิญญาณศักดิ์สิทธิ์ที่สถิตย์อยู่ ณ วัดแห่งนี้เป็นที่เคารพสักการะของชาวบ้านตั้งแต่ในละแวกใกล้วัดไปจนถึงต่างจังหวัดในแถบภาคใต้จากศรัทธาที่เชื่อกันว่า “ขอได้ไหว้รับ” โดยเฉพาะโชคลาภ และการค้าขาย\n",
                R.drawable.h2)
        )
        myModelList.add(
            MyModel(
                "ของไหว้",
                "ในวัดเจดีย์ เต็มไปด้วยสิ่งของที่ผู้เลื่อมใสศรัทธาเอามาแก้บน เช่น รูปไก่ชน ชุดทหาร หนังสติ๊ก ของเล่นต่าง ๆ เป็นต้น ส่วนบริเวณที่ให้จุดประทัดก็มีเศษประทัดกองสูงเป็นเนินเขาย่อมๆ บ่งบอกถึงแรงศรัทธาที่มีต่อไอ้ไข่ และแสดงถึงผลสัมฤทธิ์จากผู้ที่มาขอแล้วได้รับจากไอ้ไข่ ทุกวันผู้คนต่างหลั่งไหลไปขอพรจากไอ้ไข่\n",
                R.drawable.h3)
        )
        myModelList.add(
            MyModel(
                "การบูชา ไอ้ไข่ วัดเจดีย์",
                "ธูป 3 ดอก บูชาบนได้ ไหว้รับ แต่เมื่อสำเร็จให้ แก้บนด้วยของที่นำมาบนและจุดธูปเพียง 1 ดอกเท่านั้น\n" +
                        "ของที่ชอบ : ขนมเปี๊ยะ น้ำแดง ชุดทหาร ตำรวจ ไก่ปูนปั้น หนังสติ๊ก ประทัด\n" ,
                R.drawable.h4)
        )
        myAdapter = MyAdapter(this,myModelList)
        viewPager.adapter = myAdapter
        viewPager.setPadding(100,0,100,0)
    }

}