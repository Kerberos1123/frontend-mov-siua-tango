package com.una.FrontEndTango.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuReports.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuReports : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_reports, container, false)

        // --- Boton Reports ---
        // Variable donde tenemos el boton
        val botonVolver: Button = view.findViewById(R.id.btVolver)

        // Hacer funcion del boton tras hacerle click
        botonVolver.setOnClickListener{
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_menuReports_to_menuApp)
        }
        // --- -------------- ---

        return view

    }


}