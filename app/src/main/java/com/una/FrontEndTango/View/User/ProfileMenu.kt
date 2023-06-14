package com.una.FrontEndTango.View.User

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
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

        val usernamebox : TextView = view.findViewById(R.id.textView13)
        val emailbox : TextInputEditText = view.findViewById(R.id.email_box)
        val phonebox : TextInputEditText = view.findViewById(R.id.phone_box)

        // Variables a remplazar, para usarlos al cambiar valor de cajas de texto
        var username = "TESTUSER1"; var email = "pruebatest@yahoo.com"; var phone = "8012-5050"

        //Remplazar variables por valores del usuario

        //Setear texto en cajas de texto
        usernamebox.setText(username); emailbox.setText(email); phonebox.setText(phone)

        //Boton Change Password
        val botonChangePassword : Button = view.findViewById(R.id.btnChangePassword)
        //Funcionalidad Boton Change Password
        botonChangePassword.setOnClickListener{
            // Hacer la navegacion de un fragment a otro, segun ruta encontrada en grafico de navegacion
            findNavController().navigate(R.id.action_profileMenu_to_changePassword)
        }

        //Boton Logout
        val botonLogout: Button = view.findViewById(R.id.btnLogOut)
        //Funcionalidad Boton Change Password
        botonLogout.setOnClickListener{

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