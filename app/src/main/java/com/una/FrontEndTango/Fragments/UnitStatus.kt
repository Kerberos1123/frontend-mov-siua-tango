package com.una.FrontEndTango.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.R


class UnitStatus : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_unit_status, container, false)

        // --- Boton Back ---
        // Variable donde tenemos el boton
        val botonBack: Button = view.findViewById(R.id.back_button)

        // Hacer funcion del boton tras hacerle click
        botonBack.setOnClickListener{
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_unitStatus_to_clasesProfe)
        }
        // --- -------------- ---

        return view
    }

}