package com.una.FrontEndTango.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.R


class UnitRequest : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_unit_request, container, false)

        // --- Crear una lista con opciones seleccionables ---
        val lista_display : AutoCompleteTextView = view.findViewById(R.id.lista_clases_sel) // Referencia al display
        val items_lista = resources.getStringArray(R.array.test_array) // Sacar los datos de un array de strings.xml para meter en la lista
        val adaptador_lista = getActivity()?.let { ArrayAdapter(it.getApplicationContext(),R.layout.item_lista,items_lista) } // Crear adaptador, item_lista es el diseño que tiene cada item en el dropdown
        lista_display.setAdapter(adaptador_lista) // Ponerle el adaptador a la lista
        // --- ---

        // --- Boton Select Unit ---
        // Variable donde tenemos el boton
        val botonSelectUnit: Button = view.findViewById(R.id.btSelectUnit)
        // Hacer funcion del boton tras hacerle click
        botonSelectUnit.setOnClickListener{
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_unitRequest_to_selectUnit)
        }
        // --- -------------- ---

        // --- Boton Send Request ---
        // Variable donde tenemos el boton
        val botonSend: Button = view.findViewById(R.id.btSendRequest)

        // Hacer funcion del boton tras hacerle click
        botonSend.setOnClickListener{
            // Hacer la validacion de los datos

            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_unitRequest_to_menuProfe)
        }
        // --- -------------- ---

        // --- Boton Cancel ---
        // Variable donde tenemos el boton
        val botonCancel: Button = view.findViewById(R.id.btCancelUnitRequest)

        // Hacer funcion del boton tras hacerle click
        botonCancel.setOnClickListener{
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_unitRequest_to_menuProfe)
        }
        // --- -------------- ---

        return view
    }

}