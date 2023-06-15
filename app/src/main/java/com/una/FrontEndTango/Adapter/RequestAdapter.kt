package com.una.FrontEndTango.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.una.FrontEndTango.Model.RequestResponse
import com.una.FrontEndTango.R
import com.una.FrontEndTango.databinding.FragmentRequestDetailsBinding
import com.una.FrontEndTango.databinding.LayoutRequestsBinding

class RequestAdapter : RecyclerView.Adapter<RequestViewHolder>() {

    private var requestResponseList = mutableListOf<RequestResponse>()

    fun setRequestList(requestResponseList: List<RequestResponse>){
        this.requestResponseList.clear()
        this.requestResponseList.addAll(requestResponseList.toMutableList())
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutRequestsBinding.inflate(inflater, parent, false)

        return RequestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {

        //obtenemos item actual
        val currentItem = requestResponseList[position]

        // Seteamos textos o imagenes del item actual
        //holder.binding.itemTitle.text = currentItem.asset_id.toString()
        holder.binding.itemTitle.text = currentItem.id.toString()
        holder.binding.itemDetails.text = currentItem.asset_id.toString()

        holder.itemView.setOnClickListener(){
            val bundle = bundleOf(REQUEST_ID to requestResponseList[position].id.toString())

            holder.itemView.findNavController().navigate(
                R.id.action_requestsGuarda_to_requestDetails, bundle
            )
        }

    }

    override fun getItemCount(): Int {
        //devulve tamaño de la lista de requests
        return requestResponseList.size
    }

    companion object {
        const val REQUEST_ID = "request_id"
    }

}
class RequestViewHolder(
        val binding : LayoutRequestsBinding
): RecyclerView.ViewHolder(binding.root)
