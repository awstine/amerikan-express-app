package com.example.amerikanexpress.ui.screen.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel: ViewModel(){

    private val auth = FirebaseAuth.getInstance()

    //Hold the user id and password
   val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val loginResult = MutableLiveData<Result<Boolean>>()

    //Expose the LiveData
//    val userId: LiveData<String> = _userId
//    val password: LiveData<String> = _password

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