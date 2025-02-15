package com.example.amerikanexpress.ui.screen.sighnUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow

class SignUpViewModel: ViewModel(){

    //Hold the user id and password
    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()
    private val _confirmPassword = MutableLiveData<String>()

    //Expose the LiveData
    val email: LiveData<String> = _email
    val password: LiveData<String> = _password
    val confirmPassword: LiveData<String> = _confirmPassword


    //Update the LiveData
    fun updateEmail(newEmail: String){
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String){
        _password.value = newPassword
    }

    fun updateConfirmPassword(newConfirmPassword: String){
        _confirmPassword.value = newConfirmPassword
    }
}