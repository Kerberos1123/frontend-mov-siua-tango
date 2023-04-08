package com.una.FrontEndTango.Fragments


import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.R


class CreateTicket : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_create_ticket, container, false)

        // --- Crear una lista con opciones seleccionables ---
        val lista_display : AutoCompleteTextView = view.findViewById(R.id.auto_complete_list) // Referencia al display
        val items_lista = resources.getStringArray(R.array.test_array) // Sacar los datos de un array de strings.xml para meter en la lista
        val adaptador_lista = getActivity()?.let { ArrayAdapter(it.getApplicationContext(),R.layout.item_lista,items_lista) } // Crear adaptador, item_lista es el diseño que tiene cada item en el dropdown
        lista_display.setAdapter(adaptador_lista) // Ponerle el adaptador a la lista
        // --- ---

        // --- Crear una lista con opciones seleccionables ---
        val lista_display2 : AutoCompleteTextView = view.findViewById(R.id.auto_complete_list2) // Referencia al display
        val items_lista2 = resources.getStringArray(R.array.motivos_ticket) // Sacar los datos de un array de strings.xml para meter en la lista
        val adaptador_lista2 = getActivity()?.let { ArrayAdapter(it.getApplicationContext(),R.layout.item_lista,items_lista2) } // Crear adaptador, item_lista es el diseño que tiene cada item en el dropdown
        lista_display2.setAdapter(adaptador_lista2) // Ponerle el adaptador a la lista
        // --- ---


        return view
    }

}