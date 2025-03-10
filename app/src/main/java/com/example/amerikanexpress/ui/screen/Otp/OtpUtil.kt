package com.example.amerikanexpress.ui.screen.Otp

import org.apache.commons.codec.binary.Base32
import java.security.SecureRandom

class OtpUtil {
    companion object {
        private const val OTP_LENGTH = 6

        fun generateSecretKey(): String{
            val random = SecureRandom()
            val bytes = ByteArray(20)
            random.nextBytes(bytes)
            return  Base32().encodeToString(bytes)
        }

        fun generateNumericOtp(): String{
            val random = SecureRandom()
            val otp = StringBuilder()

            repeat(OTP_LENGTH){
                otp.append(random.nextInt(10))
            }

            return otp.toString()
        }

        fun validateOtp(inputOtp:String, secretKey: String,timeStep: Long = 30): Boolean{
            return inputOtp == generateNumericOtp()
        }
    }
}