package com.herdi.yusli.glucosapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.herdi.yusli.glucosapp.R
import com.herdi.yusli.glucosapp.data.Diabetes

class TipeDiabetesAdapter(private val listDiabtes: ArrayList<Diabetes>) :
    RecyclerView.Adapter<TipeDiabetesAdapter.ListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_diabtes, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val diabetes: Diabetes = listDiabtes[position]
        holder.tipeTxt.text = diabetes.tipe
        holder.descTxt.text = diabetes.desc

        val isExpandable : Boolean = listDiabtes[position].expandable
        holder.expandableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE


        holder.linearLayout.setOnClickListener {
            val list_diabetes = listDiabtes[position]
            list_diabetes.expandable = !list_diabetes.expandable
            notifyItemChanged(position)
        }


    }

    override fun getItemCount(): Int = listDiabtes.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tipeTxt: TextView = itemView.findViewById(R.id.tipe_tipe)
        var descTxt: TextView = itemView.findViewById(R.id.penjelasan_tv)
        var linearLayout: LinearLayout = itemView.findViewById(R.id.linearLyt)
        var expandableLayout: RelativeLayout = itemView.findViewById(R.id.expand_item)

    }

}