package com.una.FrontEndTango.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.una.FrontEndTango.Repository.LoginRepository
import com.una.FrontEndTango.Service.LoginService

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            LoginViewModel(
                loginRepository = LoginRepository(
                    loginService = LoginService.getInstance()
                )
            ) as T
        } else {
            throw IllegalArgumentException("LoginModel Not Found")
        }
    }
}