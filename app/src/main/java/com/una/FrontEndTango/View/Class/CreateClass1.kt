package com.una.FrontEndTango.View.Class

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.R


class CreateClass1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_class1, container, false)

        // --- Boton NEXT ---
        val botonNext: Button = view.findViewById(R.id.btNextCreateClass)
        botonNext.setOnClickListener{ // Hacer funcion del boton tras hacerle click
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_createClass1_to_createClass2)
        }

        // --- Boton CANCEL ---
        val botonCancel: Button = view.findViewById(R.id.btCancelCreateClass)
        botonCancel.setOnClickListener{ // Hacer funcion del boton tras hacerle click
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_createClass1_to_clasesAdmin)
        }

        return view
    }

}