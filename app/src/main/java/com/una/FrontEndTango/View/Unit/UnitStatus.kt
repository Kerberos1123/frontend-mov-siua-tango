package com.una.FrontEndTango.View.Unit

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.R


class UnitStatus : Fragment() {

    var unit_state = 3 // Estado de progreso

    // Tipo de usuario que esta metido en este fragment, dependiendo de esto funciones variaran
    var tipo_usuario = 1 // 0: ADMIN - 1:GUARDA - 2:PROFE

    private fun setProgressIconsColors(view : View){ // Funcion para mostrar en que estado esta

        if(unit_state < 1){return} // Si no queremos colorear ningun circulo de progreso, se pone menor que 1

        var unitIcons : ArrayList<ImageView>
        unitIcons = arrayListOf<ImageView>()

        unitIcons.add(view.findViewById(R.id.icon_progress1))
        unitIcons.add(view.findViewById(R.id.icon_progress2))
        unitIcons.add(view.findViewById(R.id.icon_progress3))
        unitIcons.add(view.findViewById(R.id.icon_progress4))

        // Colorear los iconos de progreso
        for(i in 0..3){
            if(i <= unit_state-1){
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

        // Cajas de texto
        val caja_titulo : TextView = view.findViewById(R.id.unit_title)
        val caja_tipo_usuario : TextView = view.findViewById(R.id.textView17)
        val caja_nombre : TextView = view.findViewById(R.id.textView6)
        val caja_clase : TextView = view.findViewById(R.id.class_name)

        // Cambiar display tipo de usuario
        when(tipo_usuario)
        {
            0-> caja_tipo_usuario.setText("ADMIN")
            1-> caja_tipo_usuario.setText("GUARD")
            2-> caja_tipo_usuario.setText("TEACHER")
        }

        setProgressIconsColors(view) // Colorear los iconos de progreso

        // Iconos de progreso
        val progress_icon1: ImageView = view.findViewById(R.id.icon_progress1)
        val progress_icon2: ImageView = view.findViewById(R.id.icon_progress2)
        val progress_icon3: ImageView = view.findViewById(R.id.icon_progress3)
        val progress_icon4: ImageView = view.findViewById(R.id.icon_progress4)

        // --- Icono1 ---
        progress_icon1.setOnClickListener{
            Log.i("Test", "Test message1")
        }

        // --- Icono2 ---
        progress_icon2.setOnClickListener{
            Log.i("Test", "Test message2")

            // Si estamos en el primer estado y es un guarda, puede entregar aula
            if(unit_state == 1 && tipo_usuario == 1)
            {
                // Ventana de confirmacion
                val dialogBuilder = AlertDialog.Builder(requireActivity())
                dialogBuilder.setMessage("Do you want to open this classroom?")
                    .setCancelable(true)
                    .setPositiveButton("Yes") { dialog, _ ->
                        //requestViewModel.deleteRequestById(requestId.toLong())
                        // Entregar el aula
                        unit_state = 2 // Cambiar estado
                        setProgressIconsColors(view)
                        dialog.dismiss()
                    }
                    .setNegativeButton("No") { dialog, _ ->
                        dialog.dismiss()
                    }
                val alert = dialogBuilder.create()
                alert.setTitle("Open Classroom?")
                alert.show()
            }

            // Si estamos en el primer estado y es un profe, recibe alerta de esperar al guarda
            else if(unit_state == 1 && tipo_usuario == 2)
            {
                // Ventana de confirmacion
                val dialogBuilder = AlertDialog.Builder(requireActivity())
                dialogBuilder.setMessage("Please wait for the GUARD to open doors.")
                    .setCancelable(true)
                    .setPositiveButton("Ok") { dialog, _ ->
                        dialog.dismiss()
                    }
                val alert = dialogBuilder.create()
                alert.setTitle("Wait for the GUARD")
                alert.show()
            }
        }

        // --- Icono3 ---
        progress_icon3.setOnClickListener{
            Log.i("Test", "Test message3")

            // Si estamos en el segundo estado y es un profesor, puede regresar el aula
            if(unit_state == 2 && tipo_usuario == 2)
            {
                // Ventana de confirmacion
                val dialogBuilder = AlertDialog.Builder(requireActivity())
                dialogBuilder.setMessage("Do you want to return this classroom?")
                    .setCancelable(true)
                    .setPositiveButton("Yes") { dialog, _ ->
                        //requestViewModel.deleteRequestById(requestId.toLong())
                        // Regresar el aula
                        unit_state = 3 // Cambiar estado
                        setProgressIconsColors(view)
                        dialog.dismiss()
                    }
                    .setNegativeButton("No") { dialog, _ ->
                        dialog.dismiss()
                    }
                val alert = dialogBuilder.create()
                alert.setTitle("Return Classroom?")
                alert.show()
            }

            // Si estamos en el segundo estado y es un guarda, recibe mensaje
            if(unit_state == 2 && tipo_usuario == 1)
            {
                // Ventana de confirmacion
                val dialogBuilder = AlertDialog.Builder(requireActivity())
                dialogBuilder.setMessage("Please wait for the TEACHER to return the classroom.")
                    .setCancelable(true)
                    .setPositiveButton("Ok") { dialog, _ ->
                        dialog.dismiss()
                    }
                val alert = dialogBuilder.create()
                alert.setTitle("Wait for the TEACHER")
                alert.show()
            }

        }

        // --- Icono4 ---
        progress_icon4.setOnClickListener{
            Log.i("Test", "Test message4")

            // Si estamos en el tercer estado y es un guarda, puede cerrar el aula
            if(unit_state == 3 && tipo_usuario == 1)
            {
                // Ventana de confirmacion
                val dialogBuilder = AlertDialog.Builder(requireActivity())
                dialogBuilder.setMessage("Do you want to close this classroom?")
                    .setCancelable(true)
                    .setPositiveButton("Yes") { dialog, _ ->
                        //requestViewModel.deleteRequestById(requestId.toLong())
                        // Cerrar el aula
                        unit_state = 4 // Cambiar estado
                        setProgressIconsColors(view)
                        dialog.dismiss()
                    }
                    .setNegativeButton("No") { dialog, _ ->
                        dialog.dismiss()
                    }
                val alert = dialogBuilder.create()
                alert.setTitle("Close Classroom?")
                alert.show()
            }

        }

        return view
    }

}