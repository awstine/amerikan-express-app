package com.example.amerikanexpress.ui.screen.sighnUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amerikanexpress.ui.screen.data.RetrofitClient
import com.example.amerikanexpress.ui.screen.sighnUp.data.SignUpRequest
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpViewModel: ViewModel(){

    private val auth = FirebaseAuth.getInstance()
    private val apiService = RetrofitClient.apiService

    //Hold the user id and password
   // val email = MutableLiveData<String>()
   // val password = MutableLiveData<String>()
   // val confirmPassword = MutableLiveData<String>()
   // val signUpResult = MutableLiveData<Result<Boolean>>()

    //Expose the LiveData
//    val email: LiveData<String> = _email
//    val password: LiveData<String> = _password
//    val confirmPassword: LiveData<String> = _confirmPassword
//    val signUpResult: LiveData<Result<Boolean>> = _signUpResult

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword

    private val _signUpResult = MutableStateFlow<Result<Boolean>?>(null)
    val signUpResult: StateFlow<Result<Boolean>?> = _signUpResult


    fun updateEmail(email: String) {
        _email.value = email
    }

    fun updatePassword(password: String) {
        _password.value = password
    }

    fun updateConfirmPassword(confirmPassword: String) {
        _confirmPassword.value = confirmPassword
    }

    // Sign up user with Firebase and Retrofit
    fun signUpUser(email: String, password: String, confirmPassword: String) {
        if (password != _confirmPassword.value) {
            _signUpResult.value = Result.failure(Exception("Password does not match"))
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Firebase registration successful, now call the backend API
                    viewModelScope.launch {
                        try {
                            val response = apiService.signUpUser(SignUpRequest(email, password, confirmPassword))
                            if (response.isSuccessful) {
                                _signUpResult.value = Result.success(true)
                            } else {
                                val errorBody = response.errorBody()?.string()
                                _signUpResult.value = Result.failure(Exception(errorBody ?: "Unknown error"))
                            }
                        } catch (e: Exception) {
                            _signUpResult.value = Result.failure(e)
                        }
                    }
                } else {
                    _signUpResult.value = Result.failure(task.exception ?: Exception("Firebase sign-up failed"))
                }
            }
    }
}