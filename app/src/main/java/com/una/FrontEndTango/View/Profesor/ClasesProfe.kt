package com.una.FrontEndTango.View.Profesor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.una.FrontEndTango.R
import com.una.FrontEndTango.Adapter.RecyclerAdapterClass
import com.una.FrontEndTango.Model.Class

class ClasesProfe : Fragment() {

    //variables de referencia
    private lateinit var adapter : RecyclerAdapterClass
    private lateinit var recyclerView: RecyclerView

    private lateinit var classArrayList: ArrayList<Class>

    //Arrays para titulos y descripciones
    lateinit var titulo : Array<String>
    lateinit var descripcion :Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clases_profe, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        //se inicializa data
        dataInitialize()

        //seteamos parametros del recycle view y su layout manager
        val layoutManager = LinearLayoutManager(context)

        recyclerView = view.findViewById(R.id.recycler_clases_profe)

        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = RecyclerAdapterClass(classArrayList)
        recyclerView.adapter = adapter

        // Agregar el click listener
        adapter.setOnItemClickListener(object: RecyclerAdapterClass.onItemClickListener{
            override fun onItemClick(position: Int) { // Implementar logica al hacer click en seccion de recycler
                findNavController().navigate(R.id.action_clasesProfe_to_unitStatus)
            }
        })
        // --- ---

    }

    private fun dataInitialize(){ //inicializacion de datos


        classArrayList = arrayListOf<Class>()    //se crea arraylist que almacena el contenido de las clases

        titulo = arrayOf(
            //se inicializan datos de los titulos

            getString(R.string.T2), getString(R.string.T3)
        )
        descripcion = arrayOf( //se inicializan datos de los descripcion

            getString(R.string.D2), getString(R.string.D3)
        )

        for ( i in titulo.indices){


            //creamos objeto clase

            val `class` = Class(titulo[i],descripcion[i])

            //se añade a la lista
            classArrayList.add(`class`)
        }
    }


}