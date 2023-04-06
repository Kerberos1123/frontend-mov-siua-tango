package com.una.FrontEndTango.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.una.FrontEndTango.R
import com.una.FrontEndTango.Adapters.RecyclerAdapterRequests
import com.una.FrontEndTango.DataClasses.Request

class RequestsGuarda : Fragment() {

    //variables de referencia
    private lateinit var adapter : RecyclerAdapterRequests
    private lateinit var recyclerView: RecyclerView

    private lateinit var requestArrayList: ArrayList<Request>

    //Arrays para titulos y descripciones
    lateinit var titulo : Array<String>
    lateinit var descripcion :Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_requests_guarda, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        //se inicializa data
        dataInitialize()

        //seteamos parametros del recycle view y su layout manager
        val layoutManager = LinearLayoutManager(context)

        recyclerView = view.findViewById(R.id.reciclerView)

        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = RecyclerAdapterRequests(requestArrayList)
        recyclerView.adapter = adapter
    }
    private fun dataInitialize(){ //inicializacion de datos


        requestArrayList = arrayListOf<Request>()    //se crea arraylist que almacena el contenido de las request

        titulo = arrayOf( //se inicializan datos de los titulos

            getString(R.string.T1)
        )
        descripcion = arrayOf( //se inicializan datos de los descripcion

            getString(R.string.D1)
        )

        for ( i in titulo.indices){

            //creamos objeto request

            val request = Request(titulo[i],descripcion[i])

            //se añade a la lista
            requestArrayList.add(request)
        }
    }



}