package com.example.amerikanexpress.ui.screen.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel(){

    //Hold the user id and password
    private val _userId = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()

    //Expose the LiveData
    val userId: LiveData<String> = _userId
    val password: LiveData<String> = _password

    //Update the LiveData
    fun updateUseerId(newUserId: String){
        _userId.value = newUserId
    }

    fun updatePassword(newPassword: String){
        _password.value = newPassword
    }



}