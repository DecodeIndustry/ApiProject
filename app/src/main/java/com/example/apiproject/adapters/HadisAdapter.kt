package com.example.apiproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apiproject.R
import com.example.apiproject.databinding.ListItemBinding
import com.example.apiproject.models.HadisItemModel

class HadisAdapter(var context:Context,var list:ArrayList<HadisItemModel>):RecyclerView.Adapter<HadisAdapter.MyViewHoldr>() {

    inner class MyViewHoldr(view:View):RecyclerView.ViewHolder(view){

        val binding = ListItemBinding.bind(view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHoldr {

        val itView = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        return MyViewHoldr(itView)

    }

    override fun onBindViewHolder(holder: MyViewHoldr, position: Int) {

        holder.binding.idTxt.text = "Id ->" + list[position].id
        holder.binding.timeTxt.text = "Time ->" + list[position].time
        holder.binding.dateTxt.text = "Date ->" + list[position].date
        holder.binding.titleTxt.text = "Title ->" + list[position].title
        holder.binding.detailsTxt.text = "Details ->" + list[position].detail
        holder.binding.languageTxt.text = "Language ->" + list[position].language

    }

    override fun getItemCount(): Int {

        return list.size

    }

}