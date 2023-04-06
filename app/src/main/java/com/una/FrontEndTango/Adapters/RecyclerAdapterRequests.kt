package com.una.FrontEndTango.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.una.FrontEndTango.DataClasses.Request
import com.una.FrontEndTango.R

class RecyclerAdapterRequests(private val requestList:ArrayList<Request>) : RecyclerView.Adapter<RecyclerAdapterRequests.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val title : TextView = itemView.findViewById(R.id.itemTitle)
        val description : TextView = itemView.findViewById(R.id.itemDetails)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        //inflamos layout de requests
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.requests_layout,parent,false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //obtenemos item actual
        val currentItem = requestList[position]

        //seteamos textos o imagenes de item actual
        holder.title.text = currentItem.title
        holder.description.text = currentItem.description
    }

    override fun getItemCount(): Int {

        //devulve tamaño de la lista de requests
        return requestList.size
    }



}





