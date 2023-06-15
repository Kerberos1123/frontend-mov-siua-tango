package com.una.FrontEndTango.View.Request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.Model.Request
import com.una.FrontEndTango.Model.RequestRequest
import com.una.FrontEndTango.R
import com.una.FrontEndTango.ViewModel.RequestViewModel
import com.una.FrontEndTango.ViewModel.RequestViewModelFactory
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*


class UnitRequest : Fragment() {

    // Shared view model
    private val requestViewModel : RequestViewModel by activityViewModels(){
        RequestViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_unit_request, container, false)

        // --- Crear una lista con opciones seleccionables ---
        val lista_display : AutoCompleteTextView = view.findViewById(R.id.lista_clases_sel) // Referencia al display
        val items_lista = resources.getStringArray(R.array.ej_clases) // Sacar los datos de un array de strings.xml para meter en la lista
        val adaptador_lista = getActivity()?.let { ArrayAdapter(it.getApplicationContext(),R.layout.layout_item_lista,items_lista) } // Crear adaptador, layout_item_lista es el diseño que tiene cada item en el dropdown
        lista_display.setAdapter(adaptador_lista) // Ponerle el adaptador a la lista
        // --- ---

        // --- Boton Select Unit ---
        val botonSelectUnit: Button = view.findViewById(R.id.btSelectUnit)
        botonSelectUnit.setOnClickListener{
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_unitRequest_to_selectUnit)
        }
        // --- -------------- ---

        // --- Boton Send Request ---
        val botonSend: Button = view.findViewById(R.id.btSendRequest)

        botonSend.setOnClickListener{
            // Hacer la validacion de los datos
            val date = Date()
            var id: Long = 68 ; var asset_id: Long = 40; var clasroom_id: Long = 22; var user_id: Long = 24

            // Formar nuevo objeto con datos a agregar
            var new_request: RequestRequest = RequestRequest(null, asset_id, clasroom_id, date, user_id)

            // Agregar nuevo request a la base de datos
            requestViewModel.createRequest(new_request)

            findNavController().navigate(R.id.action_unitRequest_to_menuProfe)
        }

        // --- -------------- ---

        // --- Boton Cancel ---
        // Variable donde tenemos el boton
        val botonCancel: Button = view.findViewById(R.id.btCancelUnitRequest)

        // Hacer funcion del boton tras hacerle click
        botonCancel.setOnClickListener{
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_unitRequest_to_menuProfe)
        }
        // --- -------------- ---

        return view
    }

}