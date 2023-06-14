package com.una.FrontEndTango.View.User

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.Adapter.UserAdapter.Companion.USER_ID
import com.una.FrontEndTango.ViewModel.*
import com.una.FrontEndTango.databinding.FragmentChangePasswordBinding


class ChangePassword : Fragment() {

    // Definition of the binding variable
    private var _binding: FragmentChangePasswordBinding? = null
    private val binding get() = _binding!!

    // Shared view model
    private val userViewModel : UserViewModel by activityViewModels(){
        UserViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentChangePasswordBinding.inflate(inflater,container,false)

        val userId : String = arguments?.getString(USER_ID) ?: "0"

        userViewModel.state.observe(viewLifecycleOwner){ state ->
            // this lets us avoid repeating 'binding.frameNews' before everything
            with(binding.root) {
                when (state) {
                    // just checking equality because Loading is a -singleton object instance-
                    StateUser.Loading -> {
                        // TODO: If you need do something in loading
                    }
                    // Error and Success are both -classes- so we need to check their type with 'is'
                    is StateUser.Error -> {
                        // TODO: If you need do something in error
                    }
                    is StateUser.Success -> {
                        state.user?.let {
                            //binding.textUserName2.text = it.user_reason_id.toString()
                            //binding.editTextTextMultiLine2.setText(it.asset_type_id.toString())
                        }
                    }
                    else -> {
                        // TODO: Not state loaded
                    }
                }

            }
        }

        binding.btnCancel.setOnClickListener { findNavController().navigateUp() }

        binding.btnChange.setOnClickListener {

            val dialogBuilder = AlertDialog.Builder(requireActivity())
            dialogBuilder.setCancelable(true)

            // Iniciar verificacion de password
            var pass = "1234" // Poner en esta variable el password del usuario sacandolo de la base de datos
            val oldp = binding.inputOldPassword.text.toString()
            val newp = binding.inputNewPassword.text.toString()
            val rnewp = binding.inputRepeatNew.text.toString()
            var result = 1 // Resultado de la verificacion

            result = PasswordVerification(pass, oldp, newp, rnewp)

            when(result)
            {
                0-> { dialogBuilder.setMessage("Password changed") } // El cambio tuvo exito
                1-> { dialogBuilder.setMessage("ERROR 1. A field is empty.") } // Error
                2-> { dialogBuilder.setMessage("ERROR 2. Verification Error.") } // Error
                3-> { dialogBuilder.setMessage("ERROR 3. New passwords should be the same.") } // Error
            }

            dialogBuilder.setPositiveButton("OK") { dialog, _ ->
                //userViewModel.deleteUserById(userId.toLong())
                dialog.dismiss()
                binding.inputOldPassword.setText("")
                binding.inputOldPassword.requestFocus()
                binding.inputNewPassword.setText("")
                binding.inputRepeatNew.setText("")
                context?.let { it1 -> HideKeyboard(it1, binding.root) } // Esconder el teclado
                if(result == 0){ findNavController().navigateUp() }
            }

            val alert = dialogBuilder.create()
            alert.setTitle("CHANGE PASSWORD RESULT")
            alert.show()

        }

        //userViewModel.getUser(userId.toLong())

        // Esconder teclado tocando afuera del EditText
        binding.root.setOnClickListener { context?.let { it1 -> HideKeyboard(it1, binding.root) }} // Esconder el teclado }

        return binding.root
    }

    fun PasswordVerification( current_pass:String, old_pass: String, new_pass: String, repeat_new_pass: String ): Int {
        if(old_pass.isEmpty() || new_pass.isEmpty() || repeat_new_pass.isEmpty()){ return 1 } // Si hay algun campo vacio
        if(current_pass != old_pass){ return 2 } // Si la contrasena actual es la que escribio
        if(new_pass != repeat_new_pass){ return 3 } // Si las nuevas contrasenas no coinciden

        return 0 // Si llega aqui paso la verificacion
    }

    fun HideKeyboard(context: Context, view: View) { // Funcion para esconder teclado
        val imm: InputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}