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

class RequestAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private var requestResponseList = mutableListOf<RequestResponse>()

    fun setRequestList(requestResponseList: List<RequestResponse>){
        this.requestResponseList.clear()
        this.requestResponseList.addAll(requestResponseList.toMutableList())
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutRequestsBinding.inflate(inflater, parent, false)

        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        //obtenemos item actual
        val currentItem = requestResponseList[position]

        //seteamos textos o imagenes de item actual
        holder.binding.itemTitle.text = currentItem.item_name
        holder.binding.itemDetails.text = currentItem.classroom_name

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
class MainViewHolder(
        val binding : LayoutRequestsBinding
): RecyclerView.ViewHolder(binding.root)
