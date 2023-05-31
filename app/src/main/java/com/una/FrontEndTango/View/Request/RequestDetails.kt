package com.una.FrontEndTango.View.Request

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.R

class RequestDetails : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_request_details, container, false)

        //Boton Deny
        val botonDeny:Button = view.findViewById(R.id.btnDeny)

        //Funcionalidad temporal Boton Deny
        botonDeny.setOnClickListener{
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_requestDetails_to_requestsGuarda)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Boton Confirm
        val botonConfirm:Button =view.findViewById(R.id.btnConfirm)

        //Funcionalidad Boton Confirm
        botonConfirm.setOnClickListener{
            val popUpRequest = RequestPopUp()
            popUpRequest.show((activity as AppCompatActivity).supportFragmentManager,"popUpRequest")
        }

    }

}