package com.una.FrontEndTango.View.Admin

//IMPORTS VIEJOS
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.una.FrontEndTango.Adapter.ClassAdapter
import com.una.FrontEndTango.R
import com.una.FrontEndTango.ViewModel.ClassViewModelFactory

//IMPORTS NUEVOS
import com.una.FrontEndTango.databinding.FragmentClasesAdminBinding
import com.una.FrontEndTango.ViewModel.ClassViewModel
import com.una.FrontEndTango.ViewModel.StateClass

class ClasesAdmin : Fragment() {

    private var _binding: FragmentClasesAdminBinding? = null
    private val binding get() = _binding!!

    private val classViewModel: ClassViewModel by activityViewModels{
        ClassViewModelFactory()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val adapter = ClassAdapter()

        _binding = FragmentClasesAdminBinding.inflate(inflater,container,false)

        binding.recyclerClasesAdmin.adapter = adapter

        val layoutManager = LinearLayoutManager(context)
        binding.recyclerClasesAdmin.layoutManager = layoutManager

        //traemos todas las requests del webservice
        classViewModel.findAllClass()
        classViewModel.state.observe(viewLifecycleOwner) { state ->

            when (state) {
                StateClass.Loading -> {
                    // TODO: If you need do something in loading
                }
                is StateClass.Error -> {
                    // TODO: If you need do something in error
                }
                is StateClass.SuccessList -> {
                    state.classList?.let { adapter.setClassList(it) }
                }
                else -> {
                    // TODO: Not state loaded
                }
            }

        }

        // --- Boton CreateClass
        val botonCreateClass: Button = binding.btCreateClass
        botonCreateClass.setOnClickListener {
            findNavController().navigate(R.id.action_clasesAdmin_to_createClass2)
        }

        return binding.root

    }
}