package com.una.FrontEndTango.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.una.FrontEndTango.Model.ClassroomResponse
import com.una.FrontEndTango.databinding.LayoutRequestsBinding

class ClassroomAdapter : RecyclerView.Adapter<ClassroomViewHolder>() {

    private var classroomResponseList = mutableListOf<ClassroomResponse>()

    fun setClassroomList(classroomResponseList: List<ClassroomResponse>){
        this.classroomResponseList.clear()
        this.classroomResponseList.addAll(classroomResponseList.toMutableList())
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassroomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutRequestsBinding.inflate(inflater, parent, false)

        return ClassroomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClassroomViewHolder, position: Int) {

        //obtenemos item actual
        val currentItem = classroomResponseList[position]


        /*holder.itemView.setOnClickListener(){
            val bundle = bundleOf(REQUEST_ID to classroomResponseList[position].id.toString())

            holder.itemView.findNavController().navigate(
                R.id.action_classroomsGuarda_to_classroomDetails, bundle
            )
        }*/

    }

    override fun getItemCount(): Int {
        //devulve tamaño de la lista de classrooms
        return classroomResponseList.size
    }

    companion object {
        const val CLASSROOM_ID = "classroom_id"
    }

}
class ClassroomViewHolder(
    val binding : LayoutRequestsBinding
): RecyclerView.ViewHolder(binding.root)
