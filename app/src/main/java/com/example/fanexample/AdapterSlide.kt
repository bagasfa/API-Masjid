package com.example.fanexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterSlide(val slideList: ArrayList<NewSlide>): RecyclerView.Adapter<AdapterSlide.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val slide: NewSlide = slideList[position]
        holder?.txtJudulSlide?.text = slide.judul
        holder?.txtUrlSlide?.text = slide.url
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent?.context).inflate(R.layout.list_layout_slide, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return slideList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val txtJudulSlide = itemView.findViewById(R.id.txtJudulSlide) as TextView
        val txtUrlSlide = itemView.findViewById(R.id.txtUrlSlide) as TextView
    }
}