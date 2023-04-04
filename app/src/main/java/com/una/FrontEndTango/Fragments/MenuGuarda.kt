package com.una.FrontEndTango.Fragments

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
        val botonRequests: ImageButton = view.findViewById(R.id.btRequests)

        botonRequests.setOnClickListener {
            findNavController().navigate(R.id.action_menuGuarda_to_requestsGuarda)
        }


        return view
    }

}