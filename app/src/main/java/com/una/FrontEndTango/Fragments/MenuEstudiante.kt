package com.una.FrontEndTango.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.R

class MenuEstudiante : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_estudiante, container, false)

        //Boton My Units
        val botonMyUnits:ImageButton=view.findViewById(R.id.btMyUnits)

        //Funcionalidad Boton My Units
        botonMyUnits.setOnClickListener{
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_menuEstudiante_to_activeUnits)
        }

        //Boton Create Request
        val botonCreateRequest:ImageButton=view.findViewById(R.id.btCreateRequest)

        //Funcionalidad Boton Create Request
        botonCreateRequest.setOnClickListener{
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_menuEstudiante_to_unitRequest)
        }

        //Boton Ticket User
        val botonTicketUser:ImageButton=view.findViewById(R.id.btTicketUser)

        //Funcionalidad Boton Ticket User
        botonTicketUser.setOnClickListener{
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_menuEstudiante_to_createTicket)
        }

        return view
    }

}