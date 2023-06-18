package com.una.FrontEndTango.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.una.FrontEndTango.Model.ClassResponse
import com.una.FrontEndTango.R

import com.una.FrontEndTango.databinding.LayoutClasesBinding

class ClassAdapter : RecyclerView.Adapter<ClassViewHolder>() {

    private var ClassResponseList = mutableListOf<ClassResponse>()

    fun setClassList(ClassResponseList: List<ClassResponse>){
        this.ClassResponseList.clear()
        this.ClassResponseList.addAll(ClassResponseList.toMutableList())
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutClasesBinding.inflate(inflater, parent, false)

        return ClassViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {

        // Obtenemos el item actual
        val currentItem = ClassResponseList[position]

        holder.binding.titleClase.text = "CLASS " + currentItem.id.toString()

        holder.binding.itemDetails.text = currentItem.classClassroom.classroomName

        //holder.binding.itemState.text = "STATE: " + currentItem.

        holder.binding.itemImage.setImageResource(R.drawable.icon_group)

        holder.itemView.setOnClickListener(){
            val bundle = bundleOf(CLASS_ID to ClassResponseList[position].id.toString())

            // Conectar al destino del NavController segun el fragment se encuentra en este momento
            when(holder.itemView.findNavController().currentDestination?.id)
            {
                R.id.clasesGuarda-> holder.itemView.findNavController().navigate(R.id.action_clasesGuarda_to_unitStatus, bundle)
                R.id.clasesAdmin-> holder.itemView.findNavController().navigate(R.id.action_clasesAdmin_to_unitStatus, bundle)
                R.id.clasesProfe-> holder.itemView.findNavController().navigate(R.id.action_clasesProfe_to_unitStatus, bundle)
            }

        }

    }

    override fun getItemCount(): Int {
        // Devuelve tamaño de la lista de ClasEs
        return ClassResponseList.size
    }

    companion object {
        const val CLASS_ID = "Class_id"
    }

}
class ClassViewHolder(
    val binding: LayoutClasesBinding
): RecyclerView.ViewHolder(binding.root)
