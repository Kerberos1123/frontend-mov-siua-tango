package com.una.FrontEndTango.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.R

/**
 * A simple [Fragment] subclass.
 * Use the [MenuTicket.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuTicket : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_ticket, container, false)

        // --- Boton Cancel ---
        // Variable donde tenemos el boton
        val botonVolver: Button = view.findViewById(R.id.btVolver2)

        // Hacer funcion del boton tras hacerle click
        botonVolver.setOnClickListener{
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_menuTicket_to_menuApp)
        }
        // --- -------------- ---

        return view
    }

}