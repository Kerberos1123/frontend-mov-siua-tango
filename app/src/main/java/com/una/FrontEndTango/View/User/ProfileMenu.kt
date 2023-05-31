package com.una.FrontEndTango.View.User

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.R

class ProfileMenu : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile_menu, container, false)

        //Boton Change Password
        val botonChangePassword:Button=view.findViewById(R.id.btnChangePassword)

        //Funcionalidad Boton Change Password
        botonChangePassword.setOnClickListener{
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_profileMenu_to_changePassword)
        }


        return view
    }

}