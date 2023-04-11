package com.una.FrontEndTango.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.una.FrontEndTango.DataClasses.Ticket
import com.una.FrontEndTango.R

class RecyclerAdapterTickets(private val requestList:ArrayList<Ticket>) : RecyclerView.Adapter<RecyclerAdapterTickets.ViewHolder>(){

    // --- Listener para agregar funcionalidad a las secciones del recycler ---
    private lateinit var itemListener : onItemClickListener

    interface onItemClickListener
    {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener)
    {
        itemListener = listener
    }

    // ---  ---

    class ViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){

        val title : TextView = itemView.findViewById(R.id.itemTitle)
        val description : TextView = itemView.findViewById(R.id.itemDetails)

        // --- Agregar un ClickListener para poder hacerle click ---
        init {

            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }

        }
        // --- ---

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        //inflamos layout de requests
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_requests,parent,false)

        return ViewHolder(itemView, itemListener)
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


