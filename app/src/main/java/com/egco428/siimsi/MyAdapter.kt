package com.egco428.siimsi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.card_item.view.*

class MyAdapter(private val context: Context,private val myModelArrayList: ArrayList<MyModel>): PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return myModelArrayList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.card_item,container,false)
        val model = myModelArrayList[position]
        val title = model.title
        val data = model.data
        val image = model.image

        view.imageView.setImageResource(image)
        view.titletv.text = title
        view.datatv.text = data
//        view.setOnClickListener{
//            Toast
//        }
        container.addView(view,position)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView( `object` as View)
    }
}