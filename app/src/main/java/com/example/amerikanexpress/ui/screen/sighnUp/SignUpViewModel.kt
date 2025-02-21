package com.example.amerikanexpress.ui.screen.sighnUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import com.google.firebase.auth.FirebaseAuth

class SignUpViewModel: ViewModel(){

    private val auth = FirebaseAuth.getInstance()

    //Hold the user id and password
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()
    val signUpResult = MutableLiveData<Result<Boolean>>()

    //Expose the LiveData
//    val email: LiveData<String> = _email
//    val password: LiveData<String> = _password
//    val confirmPassword: LiveData<String> = _confirmPassword
//    val signUpResult: LiveData<Result<Boolean>> = _signUpResult



    //Update the LiveData
    fun updateEmail(newEmail: String){
        email.value = newEmail
    }

    fun updatePassword(newPassword: String){
        password.value = newPassword
    }

    fun updateConfirmPassword(newConfirmPassword: String){
        confirmPassword.value = newConfirmPassword
    }

    fun signUpUser(email: String, password: String){
        if (password != confirmPassword.value){
            signUpResult.value = Result.failure(Exception("Password does not match"))
            return
        }
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{task->
                if (task.isSuccessful){
                    signUpResult.value = Result.success(true)
                }else{
                    signUpResult.value = Result.failure(
                        task.exception?: Exception("Sign Up failed"))
                }
            }
    }

}