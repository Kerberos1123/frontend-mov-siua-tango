package com.una.FrontEndTango.View.Guarda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.R


class MenuGuarda : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_guarda, container, false)

        // --- Boton Requests
        val botonRequests: ImageButton = view.findViewById(R.id.btRequestGuarda)
        botonRequests.setOnClickListener {
            findNavController().navigate(R.id.action_menuGuarda_to_requestsGuarda)
        }

        // --- Boton Report
        val botonReport: ImageButton = view.findViewById(R.id.btReportsGuarda)
        botonReport.setOnClickListener {
            findNavController().navigate(R.id.action_menuGuarda_to_createReport)
        }

        // --- Boton Ticket
        val botonTicket: ImageButton = view.findViewById(R.id.btTicketGuarda)
        botonTicket.setOnClickListener {
            findNavController().navigate(R.id.action_menuGuarda_to_createTicket)
        }

        // --- Boton Clases
        val botonClases: ImageButton = view.findViewById(R.id.btActiveUnitsGuarda) // Se intercambio el boton de clases y active units a ultima hora
        botonClases.setOnClickListener {
            findNavController().navigate(R.id.action_menuGuarda_to_clasesGuarda)
        }


        return view
    }

}