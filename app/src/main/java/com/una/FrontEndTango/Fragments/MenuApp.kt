package com.una.FrontEndTango.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.R

class MenuApp : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_user, container, false)

        // --- Boton Requests ---
        // Variable donde tenemos el boton
        val botonRequests: ImageButton = view.findViewById(R.id.btClasesProfe)

        // Hacer funcion del boton tras hacerle click
        botonRequests.setOnClickListener{
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_menuApp_to_menuRequests)
        }
        // --- -------------- ---

        // --- Boton Reports ---
        // Variable donde tenemos el boton
        val botonReports: ImageButton = view.findViewById(R.id.btReports)

        // Hacer funcion del boton tras hacerle click
        botonReports.setOnClickListener{
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_menuApp_to_menuReports)
        }
        // --- -------------- ---

        // --- Boton Active Units ---
        // Variable donde tenemos el boton
        val botonActiveUnits: ImageButton = view.findViewById(R.id.btActiveUnits)

        // Hacer funcion del boton tras hacerle click
        botonActiveUnits.setOnClickListener{
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_menuApp_to_menuProfe)
        }
        // --- -------------- ---

        // --- Boton Ticket ---
        // Variable donde tenemos el boton
        val botonTicket: ImageButton = view.findViewById(R.id.btTicket)

        // Hacer funcion del boton tras hacerle click
        botonTicket.setOnClickListener{
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_menuApp_to_menuTicket)
        }
        // --- -------------- ---


        return view
    }


}