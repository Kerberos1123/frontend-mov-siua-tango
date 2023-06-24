package com.una.FrontEndTango.View.Class

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.R
import com.una.FrontEndTango.ViewModel.ClassViewModel
import  com.una.FrontEndTango.ViewModel.PriorityViewModel

//-------------------------
import com.una.FrontEndTango.databinding.FragmentCreateClass2Binding
import com.una.FrontEndTango.ViewModel.ClassViewModelFactory
import java.text.SimpleDateFormat
import com.una.FrontEndTango.BuildConfig
import com.una.FrontEndTango.Model.*

class CreateClass2 : Fragment() {

    //-----------------------------------BINDING

    // Definition of the binding variable
    private var _binding: FragmentCreateClass2Binding? = null
    private val binding get() = _binding!!


    // View model
    private lateinit var classViewModel: ClassViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{


        val view = inflater.inflate(R.layout.fragment_create_class2, container, false)

        _binding = FragmentCreateClass2Binding.inflate(inflater, container, false)

        // ClassViewModelFactory

        classViewModel = ViewModelProvider(this, ClassViewModelFactory())[ClassViewModel::class.java]

        //---------------------------------------------------------------------------------

        // --- Create a dropdown list with profesores ---
        val itemsLista = resources.getStringArray(R.array.ej_profes) // Get data from a string array defined in strings.xml to populate the list
        val adaptadorLista = ArrayAdapter(requireContext(), R.layout.layout_item_lista, itemsLista) // Create adapter, where layout_item_lista is the layout for each item in the dropdown
        _binding!!.listaProfes2.setAdapter(adaptadorLista) // Set the adapter for the list

        // --- Create another dropdown list with aulas ---
        val itemsLista2 = resources.getStringArray(R.array.ej_aulas) // Get data from a string array defined in strings.xml to populate the list
        val adaptadorLista2 = ArrayAdapter(requireContext(), R.layout.layout_item_lista, itemsLista2) // Create adapter, where layout_item_lista is the layout for each item in the dropdown
        _binding!!.listaAulas2.setAdapter(adaptadorLista2) // Set the adapter for the list

        //------------------------------------------------------------------------

        // --- Back button ---

        _binding!!.btCreateClass2.setOnClickListener {
            val formatter = SimpleDateFormat(BuildConfig.DATE_FORMAT)
            classViewModel.createClass(

                ClassRequest(
                    className = binding.editClassName.text.toString(),
                    classTeacher = Teacher(id = 1, firstName = binding.listaProfes2.text.toString(), lastName = "", email =binding.listaProfes2.text.toString() + "@gmail.com", password = "12345", tokenExpired = false, createDate = "2023-06-20T22:23:47.995+00:00", roleList = emptyList() ),
                    classClassroom = Classroom(id = 1, classroomName = binding.listaAulas2.text.toString(), ClassroomState(id = 22, name = "prueba" , createDate = "2023-06-20T22:23:47.995+00:00"), createDate = "2023-06-20T22:23:47.995+00:00" )



                )
            )

            findNavController().navigate(R.id.action_createClass2_to_clasesAdmin)
        }



        // --- Back button ---
        _binding!!.btBackCreateClass2.setOnClickListener { // Perform button action on click
            // Navigate from one fragment to another based on the defined navigation graph
            findNavController().navigate(R.id.action_createClass2_to_clasesAdmin)
        }


        return binding.root


    }






    //onCreateView Viejo
    /*
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_class2, container, false)

        // --- Crear una lista dropdown con opciones seleccionables ---
        val lista_display : AutoCompleteTextView = view.findViewById(R.id.lista_profes) // Referencia al display
        val items_lista = resources.getStringArray(R.array.ej_profes) // Sacar los datos de un array de strings.xml para meter en la lista
        val adaptador_lista = getActivity()?.let { ArrayAdapter(it.getApplicationContext(),R.layout.layout_item_lista,items_lista) } // Crear adaptador, layout_item_lista es el diseño que tiene cada item en el dropdown
        lista_display.setAdapter(adaptador_lista) // Ponerle el adaptador a la lista
        // --- ---

        // --- Crear una lista dropdown con opciones seleccionables ---
        val lista_display2 : AutoCompleteTextView = view.findViewById(R.id.lista_aulas) // Referencia al display
        val items_lista2 = resources.getStringArray(R.array.ej_aulas) // Sacar los datos de un array de strings.xml para meter en la lista
        val adaptador_lista2 = getActivity()?.let { ArrayAdapter(it.getApplicationContext(),R.layout.layout_item_lista,items_lista2) } // Crear adaptador, layout_item_lista es el diseño que tiene cada item en el dropdown
        lista_display2.setAdapter(adaptador_lista2) // Ponerle el adaptador a la lista
        // --- ---

        // --- Boton BACK ---
        val botonBack: Button = view.findViewById(R.id.btBackCreateClass2)
        botonBack.setOnClickListener{ // Hacer funcion del boton tras hacerle click
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_createClass2_to_clasesAdmin)
        }

        return view
    }


     */
}