package com.una.FrontEndTango.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.R


class MenuAdmin : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_admin, container, false)

        // --- Boton Clases ---
        // Variable donde tenemos el boton
        val botonClasses: ImageButton = view.findViewById(R.id.btClasesAdmin)
        botonClasses.setOnClickListener{
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_menuAdmin_to_clasesAdmin)
        }

        // --- Boton Tickets ---
        // Variable donde tenemos el boton
        val botonTickets: ImageButton = view.findViewById(R.id.btViewTicketsAdmin)
        botonTickets.setOnClickListener{
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_menuAdmin_to_ticketsAdmin)
        }


        return view
    }

}