package com.una.FrontEndTango.View.Request

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.R
import com.una.FrontEndTango.databinding.FragmentRequestDetailsBinding
import androidx.core.os.bundleOf
import com.una.FrontEndTango.Adapter.RequestAdapter
import com.una.FrontEndTango.Adapter.RequestAdapter.Companion.REQUEST_ID
import com.una.FrontEndTango.ViewModel.*
import org.json.JSONObject
import com.google.gson.Gson
import com.google.gson.JsonObject



class RequestDetails : Fragment() {

    // Definition of the binding variable
    private var _binding: FragmentRequestDetailsBinding? = null
    private val binding get() = _binding!!

    // Shared view model
    private val requestViewModel : RequestViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRequestDetailsBinding.inflate(inflater,container,false)

        val requestId : String = arguments?.getString(REQUEST_ID) ?: "0"

        requestViewModel.state.observe(viewLifecycleOwner){ state ->
            // this lets us avoid repeating 'binding.frameNews' before everything
            with(binding.root) {
                when (state) {
                    // just checking equality because Loading is a -singleton object instance-
                    StateRequest.Loading -> {
                        // TODO: If you need do something in loading
                    }
                    // Error and Success are both -classes- so we need to check their type with 'is'
                    is StateRequest.Error -> {
                        // TODO: If you need do something in error
                    }
                    is StateRequest.Success -> {
                        state.request?.let {

                            //val json = JSONObject(it)

                            // Request Details textbox
                            binding.txtResquestDetails.text = "REQUEST " + String.format("%02d", it.id)

                            // Username
                            binding.textUserName2.text = it.user.firstName + " " + it.user.lastName

                            // User Role
                            var role = it.user.roleList[0].id.toInt()
                            binding.textUserRole.text = resources.getStringArray(R.array.types)[role-1]

                            // Asset
                            binding.textUserItem.text = "CLASSROOM " + resources.getStringArray(R.array.ej_aulas)[it.classroomId?.toInt()!!]

                            // Class
                            binding.textUserClass.text = resources.getStringArray(R.array.ej_clases)[it.classroomId?.toInt()!!]

                            // Hours
                           // binding.textUserHours.text = it.dateHour.toString()
                        }
                    }
                    else -> {
                        // TODO: Not state loaded
                    }
                }

            }
        }

        binding.btnDeny.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(requireActivity())
            dialogBuilder.setMessage("Are you sure?")
                // if the dialog is cancelable
                .setCancelable(true)
                .setPositiveButton("Ok") { dialog, _ ->
                    requestViewModel.deleteRequestById(requestId.toLong())
                    findNavController().navigate(R.id.requestsGuarda)
                    dialog.dismiss()
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
            val alert = dialogBuilder.create()
            alert.setTitle("Delete Task")
            alert.show()
        }

        binding.btnConfirm.setOnClickListener {

            val bundle = bundleOf(RequestAdapter.REQUEST_ID to requestId)

            //requestViewModel.deleteRequestById(re)
            findNavController().navigate(R.id.action_requestDetails_to_requestsGuarda)

        }

        requestViewModel.getRequest(requestId.toLong())

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
            val popUpRequest = RequestPopUp()
            popUpRequest.show((activity as AppCompatActivity).supportFragmentManager,"popUpRequest")
        }

    }


     */

}