package com.una.FrontEndTango.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.una.FrontEndTango.Repository.UserRepository
import com.una.FrontEndTango.Service.UserService



@Suppress("UNCHECKED_CAST")
class UserViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            UserViewModel(
                userRepository = UserRepository(
                    userService = UserService.getInstance()
                )
            ) as T
        } else {
            throw IllegalArgumentException("UserModel Not Found")
        }
    }
}