package com.una.FrontEndTango.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.una.FrontEndTango.Model.TicketResponse
import com.una.FrontEndTango.R
import com.una.FrontEndTango.databinding.LayoutRequestsBinding

class TicketAdapter : RecyclerView.Adapter<TicketViewHolder>() {

    private var ticketResponseList = mutableListOf<TicketResponse>()

    fun setTicketList(ticketResponseList: List<TicketResponse>){
        this.ticketResponseList.clear()
        this.ticketResponseList.addAll(ticketResponseList.toMutableList())
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutRequestsBinding.inflate(inflater, parent, false)

        return TicketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {

        //obtenemos item actual
        val currentItem = ticketResponseList[position]

        // Seteamos textos o imagenes del item actual
        holder.binding.itemTitle.text = currentItem.ticket_reason_id.toString()
        holder.binding.itemDetails.text = currentItem.user_id.toString()
        holder.binding.itemImage.setImageResource(R.drawable.icon_ticket)

        holder.itemView.setOnClickListener(){
            val bundle = bundleOf(TICKET_ID to ticketResponseList[position].id.toString())

            holder.itemView.findNavController().navigate(R.id.action_ticketsAdmin_to_ticketDetails, bundle)
        }

    }

    override fun getItemCount(): Int {
        //Devuelve tamaño de la lista de tickets
        return ticketResponseList.size
    }

    companion object {
        const val TICKET_ID = "ticket_id"
    }

}
class TicketViewHolder(
    val binding : LayoutRequestsBinding
): RecyclerView.ViewHolder(binding.root)
