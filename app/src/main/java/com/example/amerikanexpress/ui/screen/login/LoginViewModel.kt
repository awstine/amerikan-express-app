package com.example.amerikanexpress.ui.screen.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class LoginViewModel(private val context: Context): ViewModel(){

    private val auth = FirebaseAuth.getInstance()
    private val userPreference = UserPreference(context)

    //Hold the user id and password
   val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val loginResult = MutableLiveData<Result<Boolean>>()

    fun loginUser(userId:String){
        viewModelScope.launch {
            userPreference.saveLoginState(true)
            userPreference.saveUserId(userId)
        }
    }

    fun updateEmail(newEmail: String){
        email.value = newEmail
    }

    fun updatePassword(newPassword: String){
        password.value = newPassword
    }

    fun login(email: String, password: String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{task->
                if(task.isSuccessful){
                    loginResult.value = Result.success(true)
                }else{
                    loginResult.value = Result.failure(
                        task.exception?:Exception("Login Failed"))
                }
            }
    }


}