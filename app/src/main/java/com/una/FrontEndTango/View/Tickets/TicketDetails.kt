package com.una.FrontEndTango.View.Ticket

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.R
import com.una.FrontEndTango.databinding.FragmentTicketDetailsBinding
import androidx.core.os.bundleOf
import com.una.FrontEndTango.Adapter.TicketAdapter
import com.una.FrontEndTango.Adapter.TicketAdapter.Companion.TICKET_ID
import com.una.FrontEndTango.ViewModel.*


class TicketDetails : Fragment() {

    // Definition of the binding variable
    private var _binding: FragmentTicketDetailsBinding? = null
    private val binding get() = _binding!!

    // Shared view model
    private val ticketViewModel : TicketViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTicketDetailsBinding.inflate(inflater,container,false)

        val ticketId : String = arguments?.getString(TICKET_ID) ?: "0"

        ticketViewModel.state.observe(viewLifecycleOwner){ state ->
            // this lets us avoid repeating 'binding.frameNews' before everything
            with(binding.root) {
                when (state) {
                    // just checking equality because Loading is a -singleton object instance-
                    StateTicket.Loading -> {
                        // TODO: If you need do something in loading
                    }
                    // Error and Success are both -classes- so we need to check their type with 'is'
                    is StateTicket.Error -> {
                        // TODO: If you need do something in error
                    }
                    is StateTicket.Success -> {
                        state.ticket?.let {
                            binding.textUserName2.text = it.ticket_reason_id.toString()
                            binding.editTextTextMultiLine2.setText(it.id.toString())
                        }
                    }
                    else -> {
                        // TODO: Not state loaded
                    }
                }

            }
        }

        ticketViewModel.getTicket(ticketId.toLong())

        return binding.root
    }


    //-------------     Comentado para binding y mockapi
    /*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Boton Confirm
        val botonConfirm:Button =view.findViewById(R.id.btnConfirm)

        //Funcionalidad Boton Confirm
        botonConfirm.setOnClickListener{
            val popUpTicket = TicketPopUp()
            popUpTicket.show((activity as AppCompatActivity).supportFragmentManager,"popUpTicket")
        }

    }


     */

}