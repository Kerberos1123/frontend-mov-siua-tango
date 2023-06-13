package com.una.FrontEndTango.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.una.FrontEndTango.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding // creo mi variable que utiliza el metodo ActivityMainBinding que me remite a al layout activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setContentView(R.layout.activity_main), en lugar de usar este se usa lo de arriba con el binding


        binding.btLogin.setOnClickListener{   //Click boton login

            //traemos valores de imput
            val email = binding.etEmail.text.toString().trim() //trim() es para eliminar espacios extra si los hubiera
            val password = binding.etPassword.text.toString().trim()

            //validacion

            if(email.isEmpty()){ //si esta vacio devuelve true

                binding.etEmail.error = "Enter Email" //msj de error
                binding.etEmail.requestFocus() // para seleccionar el campo

            }else if( password.isEmpty()) {

                binding.etPassword.error = "Enter Password"
                binding.etPassword.requestFocus()

            }else{ //los dos campos estan llenos

                //abre la vista de dashboard
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                finish()
            }
        }


    }
}