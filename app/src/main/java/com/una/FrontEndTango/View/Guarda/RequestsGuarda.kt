package com.una.FrontEndTango.View.Guarda

//IMPORTS VIEJOS
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.una.FrontEndTango.ViewModel.RequestViewModel
import com.una.FrontEndTango.ViewModel.RequestViewModelFactory

//IMPORTS NUEVOS
import com.una.FrontEndTango.databinding.FragmentRequestsGuardaBinding
import com.una.FrontEndTango.Adapter.RequestAdapter
import com.una.FrontEndTango.ViewModel.StateRequest

class RequestsGuarda : Fragment() {

    private var _binding: FragmentRequestsGuardaBinding? = null
    private val binding get() = _binding!!

    private val requestViewModel: RequestViewModel by activityViewModels{
        RequestViewModelFactory()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val adapter = RequestAdapter()

        _binding = FragmentRequestsGuardaBinding.inflate(inflater,container,false)

        binding.reciclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(context)
        binding.reciclerView.layoutManager = layoutManager

        //traemos todas las requests del webservice
        requestViewModel.findAllRequest()
        requestViewModel.state.observe(viewLifecycleOwner) { state ->

            when (state) {
                StateRequest.Loading -> {
                    // TODO: If you need do something in loading
                }
                is StateRequest.Error -> {
                    // TODO: If you need do something in error
                }
                is StateRequest.SuccessList -> {
                    state.requestList?.let { adapter.setRequestList(it) }
                }
                else -> {
                    // TODO: Not state loaded
                }
            }

        }

        return binding.root

    }
}