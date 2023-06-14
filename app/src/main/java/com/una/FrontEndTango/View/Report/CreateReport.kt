package com.una.FrontEndTango.View.Report

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.compose.ui.node.getOrAddAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.una.FrontEndTango.R


class CreateReport : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            // Inflate the layout for this fragment
            val view = inflater.inflate(R.layout.fragment_create_report, container, false)

            // --- Crear una lista dropdown con opciones seleccionables ---
            val lista_display : AutoCompleteTextView = view.findViewById(R.id.auto_complete_list2) // Referencia al display
            val items_lista = resources.getStringArray(R.array.test_array) // Sacar los datos de un array de strings.xml para meter en la lista
            val adaptador_lista = getActivity()?.let { ArrayAdapter(it.getApplicationContext(),R.layout.layout_item_lista,items_lista) } // Crear adaptador, layout_item_lista es el diseño que tiene cada item en el dropdown
            lista_display.setAdapter(adaptador_lista) // Ponerle el adaptador a la lista
            // ------  ------- ------ ------ ------

            // Boton Create
            val botonCreate: Button = view.findViewById(R.id.btCreateReport)
            botonCreate.setOnClickListener{ // Hacer funcion del boton tras hacerle click
                // --- Recobrar los datos para crear nuevo dato en la base de datos ---
                var unit_selected = lista_display.editableText.toString() // Tipo de unidad seleccionada
                var details: String = view.findViewById<EditText?>(R.id.new_report_details).text.toString() // String con detalles proveidos por usuario
                Log.i("Unit type", unit_selected) // Prueba
                findNavController().navigateUp()
            }

            // --- Boton Cancel ---
            val botonCancel: Button = view.findViewById(R.id.btCancelReport)
            botonCancel.setOnClickListener{ // Hacer funcion del boton tras hacerle click
                findNavController().navigateUp()
            }

            // Esconder teclado tocando afuera del EditText
            view.setOnClickListener { it.hideKeyboard() }

            return view
    }

    @SuppressLint("ServiceCast")
    fun View.hideKeyboard() { // Esconder teclado
        val inputMethodManager = requireContext().getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(this.windowToken, 0)
    }

}