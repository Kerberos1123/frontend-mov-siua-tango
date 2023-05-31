package com.una.FrontEndTango.View.Profesor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.R


class MenuProfe : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_profe, container, false)

        // --- Boton Clases ---
        // Variable donde tenemos el boton
        val botonClases: ImageButton = view.findViewById(R.id.btClasesProfe)

        // Hacer funcion del boton tras hacerle click
        botonClases.setOnClickListener{
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_menuProfe_to_clasesProfe)
        }
        // --- -------------- ---

        // --- Boton Request ---
        // Variable donde tenemos el boton
        val botonRequest: ImageButton = view.findViewById(R.id.btRequestProfe)

        // Hacer funcion del boton tras hacerle click
        botonRequest.setOnClickListener{
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_menuProfe_to_unitRequest)
        }
        // --- -------------- ---

        // --- Boton Ticket ---
        // Variable donde tenemos el boton
        val botonTicket: ImageButton = view.findViewById(R.id.btTicketProfe)

        // Hacer funcion del boton tras hacerle click
        botonTicket.setOnClickListener{
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_menuProfe_to_createTicket)
        }
        // --- -------------- ---

        return view
    }

}