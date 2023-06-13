package com.una.FrontEndTango.View.Unit

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.R


class UnitStatus : Fragment() {

    var unitState = -1 // Estado de progreso

    private fun setProgressIconsColors(view : View){ // Funcion para mostrar en que estado esta

        if(unitState < 0){return} // Si no queremos colorear ningun circulo de progreso, se pone menor que 0

        var unitIcons : ArrayList<ImageView>
        unitIcons = arrayListOf<ImageView>()

        unitIcons.add(view.findViewById(R.id.icon_progress1))
        unitIcons.add(view.findViewById(R.id.icon_progress2))
        unitIcons.add(view.findViewById(R.id.icon_progress3))
        unitIcons.add(view.findViewById(R.id.icon_progress4))

        // Colorear los iconos de progreso
        for(i in 0..3){
            if(i <= unitState){
                unitIcons.get(i).setColorFilter(ContextCompat.getColor(view.context, R.color.progress_green))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_unit_status, container, false)

        setProgressIconsColors(view) // Colorear los iconos de progreso

        // --- Boton Back ---
        // Variable donde tenemos el boton
        val botonBack: Button = view.findViewById(R.id.back_button)

        // Hacer funcion del boton tras hacerle click
        botonBack.setOnClickListener{
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_unitStatus_to_clasesProfe)
        }
        // --- -------------- ---

        return view
    }

}