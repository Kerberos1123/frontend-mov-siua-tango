package com.una.FrontEndTango.View

//IMPORTS NUEVOS

import Globals
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.una.FrontEndTango.Model.LoggedInUserView
import com.una.FrontEndTango.Model.LoginRequest
import com.una.FrontEndTango.R
import com.una.FrontEndTango.ViewModel.LoginViewModel
import com.una.FrontEndTango.ViewModel.LoginViewModelFactory
import com.una.FrontEndTango.databinding.ActivityLoginBinding


// LOGIN ACTIVITY VIEJO

/*
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

 */


// LOGIN ACTIVITY NUEVO
class LoginActivity : AppCompatActivity() {

    // Definition of the binding variable
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // With View Binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // LoginViewModelFactory
        loginViewModel =
            ViewModelProvider(this, LoginViewModelFactory())[LoginViewModel::class.java]

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            binding.btLogin.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                binding.etEmail.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                binding.etPassword.error = getString(loginState.passwordError)
            }
        })

        val sharedData: Globals = Globals.instance


        loginViewModel.loginResponse.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            /*binding.loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }*/
            if (loginResult.success != null) {
                sharedData.v1 = binding.etEmail.text.toString() // Poner en variable global usuario
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)
        })

        binding.etEmail.afterTextChanged {
            loginViewModel.loginDataChanged(
                LoginRequest(
                    username = binding.etEmail.text.toString(),
                    password = binding.etPassword.text.toString()
                )
            )
        }

        binding.etPassword.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    LoginRequest(
                        username = binding.etEmail.text.toString(),
                        password = binding.etPassword.text.toString()
                    )
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            LoginRequest(
                                username = binding.etEmail.text.toString(),
                                password = binding.etPassword.text.toString()
                            )
                        )
                }
                false
            }

            binding.btLogin.setOnClickListener {
                //binding.loading.visibility = View.VISIBLE
                loginViewModel.login(
                    LoginRequest(
                        username = binding.etEmail.text.toString(),
                        password = binding.etPassword.text.toString()
                    )
                )
            }
        }

        loginViewModel.loginDataChanged(
            LoginRequest(
                username = binding.etEmail.text.toString(),
                password = binding.etPassword.text.toString()
            )
        )
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val username = model.username

        //Complete and destroy login activity once successful
        finish()

        // Initiate successful logged in experience
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        Toast.makeText(
            applicationContext,
            "$welcome $username",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {

        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
        // return login activity

    }
}
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}