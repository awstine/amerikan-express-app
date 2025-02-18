package com.example.amerikanexpress.ui.screen.Otp

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.amerikanexpress.ui.screen.OtpScreen.generateOTP
import com.example.amerikanexpress.ui.screen.OtpScreen.sendOTP

class OTPviewModel: ViewModel(){

    var otpDigits = mutableStateListOf("", "", "", "")
        private set

    var message = mutableStateOf("")
        private set

    private var generatedOTP: String = generateOTP()  //store generated otp

    fun updateOTP(index: Int, newValue: String){
        if (newValue.length <= 1) {
            otpDigits[index] = newValue
            if (newValue.isNotEmpty() && index < 3) {
                otpDigits[index + 1] = ""
            }
        }
    }
//    fun updateOTP(index: Int, value: String) {
//        if (value.length <= 1) {
//            otpDigits[index] = value
//        }

    fun resendOTP(){
        generatedOTP = generateOTP()
        sendOTP(generatedOTP)
        otpDigits.fill("")
        message.value = "OTP sent via email"
    }

    private fun generateOTP(): String {
        return (1000..9999).random().toString()
    }

    private fun sendOTP(otp: String) {
        println("OTP sent: $otp")
    }

}