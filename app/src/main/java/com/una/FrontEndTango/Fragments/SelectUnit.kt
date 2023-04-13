package com.una.FrontEndTango.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.R

class SelectUnit : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_select_unit, container, false)

        // --- Crear una lista dropdown con opciones seleccionables ---
        val lista_display : AutoCompleteTextView = view.findViewById(R.id.lista_tipos_unidad_sel) // Referencia al display
        val items_lista = resources.getStringArray(R.array.unidades_estudiante) // Sacar los datos de un array de strings.xml para meter en la lista
        val adaptador_lista = getActivity()?.let { ArrayAdapter(it.getApplicationContext(),R.layout.layout_item_lista,items_lista) } // Crear adaptador, layout_item_lista es el diseño que tiene cada item en el dropdown
        lista_display.setAdapter(adaptador_lista) // Ponerle el adaptador a la lista
        // --- ---

        // --- Crear una lista con opciones seleccionables ---
        val lista_display2 : ListView = view.findViewById(R.id.l_unidades_disponibles) // Referencia al display
        val items_lista2 = resources.getStringArray(R.array.ej_unidades) // Sacar los datos de un array de strings.xml para meter en la lista
        val adaptador_lista2 = getActivity()?.let { ArrayAdapter(it.getApplicationContext(),R.layout.layout_item_lista,items_lista2) } // Crear adaptador, layout_item_lista es el diseño que tiene cada item en el dropdown
        lista_display2.setAdapter(adaptador_lista2) // Ponerle el adaptador a la lista
        // --- ---

        // --- Boton SelectUnit ---
        // Variable donde tenemos el boton
        val botonSelectUnit: Button = view.findViewById(R.id.btSelectUnit)

        // Hacer funcion del boton tras hacerle click
        botonSelectUnit.setOnClickListener{
            // Hacer validacion de los datos

            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_selectUnit_to_unitRequest)
        }
        // --- -------------- ---

        // --- Boton Cancel ---
        // Variable donde tenemos el boton
        val botonCancel: Button = view.findViewById(R.id.btCancelSelectUnit)

        // Hacer funcion del boton tras hacerle click
        botonCancel.setOnClickListener{
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_selectUnit_to_unitRequest)
        }
        // --- -------------- ---

        return view
    }

}