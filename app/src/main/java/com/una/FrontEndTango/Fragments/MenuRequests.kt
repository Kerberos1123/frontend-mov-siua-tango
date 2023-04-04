package com.una.FrontEndTango.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.R

class MenuRequests : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_requests, container, false)

        // --- Boton Cancel ---
        // Variable donde tenemos el boton
        val botonCancel: Button = view.findViewById(R.id.btCancel)

        // Hacer funcion del boton tras hacerle click
        botonCancel.setOnClickListener{
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_menuRequests_to_menuApp)
        }
        // --- -------------- ---

        return view
    }

}