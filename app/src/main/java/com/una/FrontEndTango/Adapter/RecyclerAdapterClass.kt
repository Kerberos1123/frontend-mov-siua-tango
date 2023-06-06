package com.una.FrontEndTango.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.una.FrontEndTango.Model.Class
import com.una.FrontEndTango.R

class RecyclerAdapterClass(private val classList:ArrayList<Class>) : RecyclerView.Adapter<RecyclerAdapterClass.ViewHolder>(){

    // --- Listener para agregar funcionalidad a las secciones del recycler ---
    private lateinit var itemListener : onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        itemListener = listener
    }

    class ViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){

        val title : TextView = itemView.findViewById(R.id.titleClase)
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

        //inflamos layout de clases
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_clases_profe,parent,false)

        return ViewHolder(itemView, itemListener) // Se le agrega el listener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //obtenemos item actual
        val currentItem = classList[position]

        //seteamos textos o imagenes de item actual
        holder.title.text = currentItem.title
        holder.description.text = currentItem.description


        //aqui se puede poner un setOnClickListener

    }

    override fun getItemCount(): Int {

        //devuelve tamaño de la lista de clases
        return classList.size
    }




}

