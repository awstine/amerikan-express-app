package com.example.amerikanexpress.ui.screen.Otp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Properties
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class EmailService {
    companion object{
        suspend fun sendOtpEmail(
            recipientEmail: String,
            otp: String,
            senderEmail: String,
            senderPassword: String,
            smtpHost: String = "smtp.gmail.com",
            smtpPort: Int = 587
        ): Boolean  = withContext(Dispatchers.IO){
            try {
                val properties = Properties()
                properties["mail.smtp.host"] = smtpHost
                properties["mail.smtp.port"] = smtpPort
                properties["mail.smtp.auth"] = "true"
                properties["mail.smtp.starttls.enable"] = "true"

                val session = Session.getInstance(properties,object : Authenticator(){
                    override fun getPasswordAuthentication(): PasswordAuthentication {
                        return PasswordAuthentication(senderEmail,senderPassword)
                    }
                })

                val message = MimeMessage(session)
                message.setFrom(InternetAddress(senderEmail))
                message.addRecipient(Message.RecipientType.TO,InternetAddress(recipientEmail))
                message.subject = "Your OTP code"
                message.setText("Your OTP code is: $otp\n\nThis OTP code will expire in 10 minutes")

                Transport.send(message)
                true
            }catch (e: Exception){
                e.printStackTrace()
                false
            }
        }
    }
}