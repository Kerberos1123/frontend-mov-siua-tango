package com.una.FrontEndTango.View.Tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.una.FrontEndTango.Adapter.RecyclerAdapterTickets
import com.una.FrontEndTango.R
import com.una.FrontEndTango.Model.Ticket

class TicketsAdmin : Fragment() {

    //variables de referencia
    private lateinit var adapter : RecyclerAdapterTickets
    private lateinit var recyclerView: RecyclerView

    private lateinit var requestArrayList: ArrayList<Ticket>

    //Arrays para titulos y descripciones
    lateinit var titulo : Array<String>
    lateinit var descripcion :Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tickets_admin, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        //se inicializa data
        dataInitialize()

        //seteamos parametros del recycle view y su layout manager
        val layoutManager = LinearLayoutManager(context)

        recyclerView = view.findViewById(R.id.recycler_tickets_admin)

        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = RecyclerAdapterTickets(requestArrayList)
        recyclerView.adapter = adapter


        // Agregar el click listener
        adapter.setOnItemClickListener(object: RecyclerAdapterTickets.onItemClickListener{
            override fun onItemClick(position: Int) { // Implementar logica al hacer click en seccion de recycler
                //findNavController().navigate(R.id.action_requestsGuarda_to_requestDetails)
            }
        })
        // --- ---

    }
    private fun dataInitialize(){ //inicializacion de datos


        requestArrayList = arrayListOf<Ticket>()    //se crea arraylist que almacena el contenido de las request

        titulo = arrayOf( //se inicializan datos de los titulos

            getString(R.string.T1)
        )
        descripcion = arrayOf( //se inicializan datos de los descripcion

            getString(R.string.D1)
        )

        for ( i in titulo.indices){

            //creamos objeto ticket

            val ticket = Ticket(titulo[i],descripcion[i])

            //se añade a la lista
            requestArrayList.add(ticket)
        }
    }



}