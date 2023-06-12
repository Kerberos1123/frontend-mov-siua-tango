package com.una.FrontEndTango.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.una.FrontEndTango.Model.UserResponse
import com.una.FrontEndTango.databinding.LayoutRequestsBinding

class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {

    private var userResponseList = mutableListOf<UserResponse>()

    fun setUserList(userResponseList: List<UserResponse>){
        this.userResponseList.clear()
        this.userResponseList.addAll(userResponseList.toMutableList())
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutRequestsBinding.inflate(inflater, parent, false)

        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        //obtenemos item actual
        val currentItem = userResponseList[position]


        /*holder.itemView.setOnClickListener(){
            val bundle = bundleOf(REQUEST_ID to userResponseList[position].id.toString())

            holder.itemView.findNavController().navigate(
                R.id.action_usersGuarda_to_userDetails, bundle
            )
        }*/

    }

    override fun getItemCount(): Int {
        //devulve tamaño de la lista de users
        return userResponseList.size
    }

    companion object {
        const val USER_ID = "user_id"
    }

}
class UserViewHolder(
    val binding : LayoutRequestsBinding
): RecyclerView.ViewHolder(binding.root)
