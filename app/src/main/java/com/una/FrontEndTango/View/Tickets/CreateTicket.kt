package com.una.FrontEndTango.View.Tickets


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.R





class CreateTicket : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_create_ticket, container, false)


        // --- Crear una lista dropdown con opciones seleccionables ---
        val lista_display2 : AutoCompleteTextView = view.findViewById(R.id.auto_complete_list2) // Referencia al display
        val items_lista2 = resources.getStringArray(R.array.motivos_ticket) // Sacar los datos de un array de strings.xml para meter en la lista
        val adaptador_lista2 = getActivity()?.let { ArrayAdapter(it.getApplicationContext(),R.layout.layout_item_lista,items_lista2) } // Crear adaptador, layout_item_lista es el diseño que tiene cada item en el dropdown
        lista_display2.setAdapter(adaptador_lista2) // Ponerle el adaptador a la lista
        // --- ---

        // --- Boton Create ---
        val botonCreate: Button = view.findViewById(R.id.btCreateTicket)
        botonCreate.setOnClickListener{ // Hacer funcion del boton tras hacerle click
            // --- Recobrar los datos para crear nuevo dato en la base de datos ---
            var reason: String = lista_display2.editableText.toString() // Motivo del ticket
            var details: String = view.findViewById<EditText?>(R.id.editTextTextMultiLine3).text.toString() // String con detalles proveidos por usuario
            //Log.i("Prueba", details) // Prueba
            findNavController().navigateUp() // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
        }
        // --- -------------- ---


        // --- Boton Cancel ---
        val botonCancel: Button = view.findViewById(R.id.btCancelTicket)
        botonCancel.setOnClickListener{ // Hacer funcion del boton tras hacerle click
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigateUp()
        }
        // --- -------------- ---

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