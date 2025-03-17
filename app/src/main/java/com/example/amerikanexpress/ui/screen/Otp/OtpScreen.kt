package com.example.amerikanexpress.ui.screen.OtpScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.amerikanexpress.ui.screen.Otp.OTPviewModel

@Composable
fun OTPScreen(
    navController: NavController
) {
//    val viewModel: OTPviewModel = remember { OTPviewModel() }
//    val otpDigits = viewModel.otpDigits

//    val focusRequesters = remember { List(4) { FocusRequester() } }

    var otpValue by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }


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
        BasicTextField(
            value = otpValue,
            onValueChange = {
                if (it.length <= 4) {
                    otpValue = it
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword
            ),
            decorationBox = { innerTextField ->
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(4) { index ->
                        val char = when {
                            index >= otpValue.length -> ""
                            else -> otpValue[index].toString()
                        }
                        val isFocused = otpValue.length == index
                        Box(
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp)
                                .border(
                                    width = if (isFocused) 2.dp else 1.dp,
                                    color = if (isFocused) Color.Black else Color.Gray,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(2.dp),
                            contentAlignment = Alignment.Center // Center the content inside the Box
                        ) {
                            Text(
                                text = char,
                                style = MaterialTheme.typography.headlineSmall,
                                textAlign = TextAlign.Center
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        )


//        Row(
//            horizontalArrangement = Arrangement.spacedBy(8.dp),
//            modifier = Modifier.wrapContentWidth()
//        ) {
//            otpDigits.forEachIndexed { index, digit ->
//                OTPTextField(
//                    value = digit,
//                    onValueChange = { newValue ->
//                        if (newValue.length <= 1) {
//                            otpDigits[index] = newValue
//                            if (newValue.isNotEmpty() && index < 3) {
//                                focusRequesters[index + 1].requestFocus()
//                            }
//                        }
//                    },
//                    focusRequester = focusRequesters[index],
//                    modifier = Modifier.width(48.dp)
//                )
//            }
//        }

        Spacer(modifier = Modifier.height(24.dp))


        // Verify Button


        Button(onClick = {},
            modifier = Modifier
            .padding(26.dp)
            .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("Verify")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Resend OTP Button
        TextButton(onClick = {
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

//0700142012