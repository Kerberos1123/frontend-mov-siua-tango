package com.una.FrontEndTango.View.Tickets

//IMPORTS VIEJOS
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.una.FrontEndTango.ViewModel.TicketViewModel
import com.una.FrontEndTango.ViewModel.TicketViewModelFactory

//IMPORTS NUEVOS
import com.una.FrontEndTango.databinding.FragmentTicketsAdminBinding
import com.una.FrontEndTango.Adapter.TicketAdapter
import com.una.FrontEndTango.ViewModel.StateTicket

class TicketsAdmin : Fragment() {

    private var _binding: FragmentTicketsAdminBinding? = null
    private val binding get() = _binding!!

    private val ticketViewModel: TicketViewModel by activityViewModels{
        TicketViewModelFactory()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val adapter = TicketAdapter()

        _binding = FragmentTicketsAdminBinding.inflate(inflater,container,false)

        binding.recyclerTicketsAdmin.adapter = adapter

        val layoutManager = LinearLayoutManager(context)
        binding.recyclerTicketsAdmin.layoutManager = layoutManager

        // Traemos todas los tickets del webservice
        ticketViewModel.findAllTicket()
        ticketViewModel.state.observe(viewLifecycleOwner) { state ->

            when (state) {
                StateTicket.Loading -> {
                    // TODO: If you need do something in loading
                }
                is StateTicket.Error -> {
                    // TODO: If you need do something in error
                }
                is StateTicket.SuccessList -> {
                    state.ticketList?.let { adapter.setTicketList(it) }
                }
                else -> {
                    // TODO: Not state loaded
                }
            }

        }

        return binding.root

    }
}