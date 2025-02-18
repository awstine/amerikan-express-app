package com.example.amerikanexpress.ui.screen.OtpScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.amerikanexpress.ui.screen.Otp.OTPviewModel

@Composable
fun OTPScreen(
    navController: NavController
) {
    val viewModel: OTPviewModel = remember { OTPviewModel() }
    val otpDigits = viewModel.otpDigits
    var message = viewModel.message.value
    val focusRequesters = remember { List(4) { FocusRequester() } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Verification",
            fontWeight = FontWeight.Bold,
            fontSize = 19.sp
        )
        Text(" OTP message been sent via email")
        Spacer(Modifier.height(60.dp))

        // OTP Input Fields (4 Boxes)
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.wrapContentWidth()
        ) {
            otpDigits.forEachIndexed { index, digit ->
                OTPTextField(
                    value = digit,
                    onValueChange = { newValue ->
                        if (newValue.length <= 1) {
                            otpDigits[index] = newValue
                            if (newValue.isNotEmpty() && index < 3) {
                                focusRequesters[index + 1].requestFocus()
                            }
                        }
                    },
                    focusRequester = focusRequesters[index],
                    modifier = Modifier.width(48.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))


        // Verify Button

        Button(onClick = {
            val otp = otpDigits.joinToString("")
            if (otp.length == 4) {
                message = if (verifyOTP(otp)) {
                    "OTP verified successfully!"
                } else {
                    "Invalid OTP. Please try again."
                }
            } else {
                message = "Please enter a 4-digit OTP."
            }
        }, modifier = Modifier
            .padding(26.dp)
            .fillMaxWidth(),
            shape = RoundedCornerShape(6.dp)
        ) {
            Text("Verify")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Resend OTP Button
        TextButton(onClick = {
            val newOTP = generateOTP()
            sendOTP(newOTP) // Simulate sending OTP
            otpDigits.fill("") // Clear the OTP fields
            focusRequesters[0].requestFocus() // Focus on the first field
            message = "New OTP sent!"
        }) {
            Text("Resend OTP")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Message Display
        Text(text = message, fontSize = 16.sp, color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
fun OTPTextField(
    value: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .focusRequester(focusRequester),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        decorationBox = { innerTextField ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(48.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.shapes.small)
            ) {
                innerTextField()
            }
        }
    )
}

// Simulate OTP verification logic
fun verifyOTP(otp: String): Boolean {
    // Replace with actual verification logic (e.g., compare with stored OTP)
    return otp == "1234" // Example: Hardcoded OTP for testing
}

// Simulate OTP generation
fun generateOTP(): String {
    return (1000..9999).random().toString() // Generate a random 4-digit OTP
}

// Simulate sending OTP (e.g., via SMS or email)
fun sendOTP(otp: String) {
    println("OTP sent: $otp") // Replace with actual sending logic
}