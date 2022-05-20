package com.herdi.yusli.glucosapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.herdi.yusli.glucosapp.R
import com.herdi.yusli.glucosapp.data.Dev

class ListDevAdapter(private val listDev: ArrayList<Dev>) :
    RecyclerView.Adapter<ListDevAdapter.ListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_dev, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, role, photo, email) = listDev[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvRole.text = role
        holder.tvEmail.text = email
    }

    override fun getItemCount(): Int = listDev.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvRole: TextView = itemView.findViewById(R.id.tv_item_role)
        var tvEmail: TextView = itemView.findViewById(R.id.tv_item_email)
    }

}