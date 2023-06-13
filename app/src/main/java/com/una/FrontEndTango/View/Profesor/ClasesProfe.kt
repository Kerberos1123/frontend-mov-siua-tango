package com.una.FrontEndTango.View.Profesor

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
import com.una.FrontEndTango.databinding.FragmentClasesProfeBinding
import com.una.FrontEndTango.ViewModel.ClassViewModel
import com.una.FrontEndTango.ViewModel.StateClass

class ClasesProfe : Fragment() {

    private var _binding: FragmentClasesProfeBinding? = null
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

        _binding = FragmentClasesProfeBinding.inflate(inflater,container,false)

        binding.recyclerClasesProfe.adapter = adapter

        val layoutManager = LinearLayoutManager(context)
        binding.recyclerClasesProfe.layoutManager = layoutManager

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


        return binding.root

    }
}