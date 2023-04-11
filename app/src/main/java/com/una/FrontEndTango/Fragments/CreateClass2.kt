package com.una.FrontEndTango.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.R


class CreateClass2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_class2, container, false)

        // --- Crear una lista dropdown con opciones seleccionables ---
        val lista_display : AutoCompleteTextView = view.findViewById(R.id.lista_profes) // Referencia al display
        val items_lista = resources.getStringArray(R.array.ej_profes) // Sacar los datos de un array de strings.xml para meter en la lista
        val adaptador_lista = getActivity()?.let { ArrayAdapter(it.getApplicationContext(),R.layout.item_lista,items_lista) } // Crear adaptador, item_lista es el diseño que tiene cada item en el dropdown
        lista_display.setAdapter(adaptador_lista) // Ponerle el adaptador a la lista
        // --- ---

        // --- Crear una lista dropdown con opciones seleccionables ---
        val lista_display2 : AutoCompleteTextView = view.findViewById(R.id.lista_aulas) // Referencia al display
        val items_lista2 = resources.getStringArray(R.array.ej_aulas) // Sacar los datos de un array de strings.xml para meter en la lista
        val adaptador_lista2 = getActivity()?.let { ArrayAdapter(it.getApplicationContext(),R.layout.item_lista,items_lista2) } // Crear adaptador, item_lista es el diseño que tiene cada item en el dropdown
        lista_display2.setAdapter(adaptador_lista2) // Ponerle el adaptador a la lista
        // --- ---

        // --- Boton BACK ---
        val botonBack: Button = view.findViewById(R.id.btBackCreateClass2)
        botonBack.setOnClickListener{ // Hacer funcion del boton tras hacerle click
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_createClass2_to_createClass1)
        }

        return view
    }

}