package com.una.FrontEndTango.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.una.FrontEndTango.Model.ReportResponse
import com.una.FrontEndTango.databinding.LayoutRequestsBinding

class ReportAdapter : RecyclerView.Adapter<ReportViewHolder>() {

    private var reportResponseList = mutableListOf<ReportResponse>()

    fun setReportList(reportResponseList: List<ReportResponse>){
        this.reportResponseList.clear()
        this.reportResponseList.addAll(reportResponseList.toMutableList())
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutRequestsBinding.inflate(inflater, parent, false)

        return ReportViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {

        //obtenemos item actual
        val currentItem = reportResponseList[position]

        // Seteamos textos o imagenes del item actual
        holder.binding.itemTitle.text = currentItem.title
        holder.binding.itemDetails.text = currentItem.description

        holder.itemView.setOnClickListener(){
            val bundle = bundleOf(REPORT_ID to reportResponseList[position].id.toString())

            //holder.itemView.findNavController().navigate(
            //    R.id.action_reportsGuarda_to_reportDetails, bundle
            //)
        }

    }

    override fun getItemCount(): Int {
        //Devuelve tamaño de la lista de reports
        return reportResponseList.size
    }

    companion object {
        const val REPORT_ID = "report_id"
    }

}
class ReportViewHolder(
    val binding : LayoutRequestsBinding
): RecyclerView.ViewHolder(binding.root)
