package com.egco428.siimsi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(private var imword:List<Int>,private  var image:List<Int>):RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>() {
    inner class  Pager2ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val itemWordImage:ImageView = itemView.findViewById(R.id.howwordIm)
        val itemImage:ImageView = itemView.findViewById(R.id.howIm)
//        init{
//            itemImage.setOnClickListener { v:View ->
//                val position = adapterPosition
//                Toast.makeText(itemView.context,"click on item",Toast.LENGTH_SHORT).show()
//            }
//        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.Pager2ViewHolder {
        return  Pager2ViewHolder((LayoutInflater.from(parent.context).inflate(R.layout.item_page,parent,false)))
    }

    override fun getItemCount(): Int {
        return imword.size    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.Pager2ViewHolder, position: Int) {
        holder.itemWordImage.setImageResource(imword[position])
        holder.itemImage.setImageResource(image[position])
    }
}