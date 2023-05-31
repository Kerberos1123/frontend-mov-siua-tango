package com.una.FrontEndTango.View.Report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.R


class CreateReport : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_report, container, false)

        // --- Boton Cancel ---
        val botonCancel: Button = view.findViewById(R.id.btCancelReport)
        botonCancel.setOnClickListener{ // Hacer funcion del boton tras hacerle click
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_createReport_to_menuGuarda)
        }

        return view
    }

}