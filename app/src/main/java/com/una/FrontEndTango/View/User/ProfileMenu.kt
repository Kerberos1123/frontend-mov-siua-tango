package com.una.FrontEndTango.View.User

import Globals
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.graphics.green
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.una.FrontEndTango.R
import com.una.FrontEndTango.View.LoginActivity
import com.una.FrontEndTango.View.MainActivity

class ProfileMenu : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile_menu, container, false)

        // Cajas de texto del fragment
        val userrolebox : TextView = view.findViewById(R.id.textView18)
        val usernamebox : TextView = view.findViewById(R.id.textView13)
        val emailbox : TextInputEditText = view.findViewById(R.id.email_box)
        val phonebox : TextInputEditText = view.findViewById(R.id.phone_box)

        // Variables a remplazar, para usarlos al cambiar valor de cajas de texto
        var role = "ROLE"; var username = "TESTUSER1"; var email = "pruebatest@yahoo.com"; var phone = "8012-5050"

        val users: ArrayList<String> =  resources.getStringArray(R.array.users).toList() as ArrayList<String>
        val types: ArrayList<String> =  resources.getStringArray(R.array.usertypes).toList() as ArrayList<String>

        //Remplazar variables por valores del usuario
        val sharedData: Globals = Globals.instance
        var type = types[users.indexOf(sharedData.value.toString())]
        role = resources.getStringArray(R.array.types).get(type.toInt()).toString()
        email = sharedData.value.toString()

        //Setear texto en cajas de texto
        userrolebox.setText(role); usernamebox.setText(username); emailbox.setText(email); phonebox.setText(phone)

        //Boton Change Password
        val botonChangePassword : Button = view.findViewById(R.id.btnChangePassword)
        botonChangePassword.setOnClickListener{ // Funcionalidad Boton Change Password
            findNavController().navigate(R.id.action_profileMenu_to_changePassword)
        }

        //Boton Logout
        val botonLogout: Button = view.findViewById(R.id.btnLogOut)

        botonLogout.setOnClickListener{ // Funcionalidad Boton Change Password

            val dialogBuilder = AlertDialog.Builder(requireActivity())
            dialogBuilder.setMessage("Are you sure?")
                // if the dialog is cancelable
                .setCancelable(true)
                .setPositiveButton("Yes") { dialog, _ ->
                    dialog.dismiss()
                    // Salir de la cuenta y pasar al LoginActivity
                    val intent = Intent(this.activity, LoginActivity::class.java)
                    startActivity(intent)
                    this.activity?.finish()

                }
                .setNegativeButton("No") { dialog, _ ->
                    // Cancelar
                    dialog.dismiss()
                }
            val alert = dialogBuilder.create()
            alert.setTitle("LogOut")
            alert.show()

        }


        return view
    }

}