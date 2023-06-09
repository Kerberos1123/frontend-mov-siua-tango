package com.una.FrontEndTango.View.Request

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.R
import com.una.FrontEndTango.Repository.TaskRepository
import com.una.FrontEndTango.Service.TaskService
import com.una.FrontEndTango.ViewModel.TaskViewModel
import com.una.FrontEndTango.ViewModel.TaskViewModelFactory
import com.una.FrontEndTango.databinding.FragmentRequestDetailsBinding
import com.una.FrontEndTango.Adapter.TaskAdapter.Companion.TASK_ID
import com.una.FrontEndTango.ViewModel.StateTask
import androidx.compose.runtime.getValue
import com.una.FrontEndTango.databinding.ActivityMainBinding


class RequestDetails : Fragment() {

    private lateinit var binding : FragmentRequestDetailsBinding
    private val taskViewModel: TaskViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRequestDetailsBinding.inflate(layoutInflater)
        val view = binding.root

        //binding.TaskList.adapter = adapter

        //Retrofit
        //val taskService = TaskService.getInstance()
        //val taskRepository = TaskRepository(taskService)

        //ViewModelFactory
        //var taskViewModel = ViewModelProvider(this, TaskViewModelFactory(taskRepository))[TaskViewModel::class.java]

        //taskViewModel.state.observe(this){
        //    binding.textUserName2.text = it.title
        //    binding.textUserRole.text = it.notes
        //}

        val taskId: String = arguments?.getString(TASK_ID) ?: "1"

        // Observer method to bind data of task into text views
        taskViewModel.state.observe(viewLifecycleOwner) { state ->
            // this lets us avoid repeating 'binding.frameNews' before everything
            with(binding.root) {
                when (state) {
                    // just checking equality because Loading is a -singleton object instance-
                    StateTask.Loading -> {
                        // TODO: If you need do something in loading
                    }
                    // Error and Success are both -classes- so we need to check their type with 'is'
                    is StateTask.Error -> {
                        // TODO: If you need do something in error
                    }
                    is StateTask.Success -> {
                        state.task?.let {
                            binding.textUserName2.text = it.title
                            binding.textUserRole.text = it.notes
                        }
                    }
                    else -> {
                        // TODO: Not state loaded
                    }
                }
            }
        }

        //Boton Deny
        val botonDeny: Button = binding.btnDeny

        //Funcionalidad temporal Boton Deny
        botonDeny.setOnClickListener{
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_requestDetails_to_requestsGuarda)
        }

        //taskViewModel.getTask(taskId.toLong())

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