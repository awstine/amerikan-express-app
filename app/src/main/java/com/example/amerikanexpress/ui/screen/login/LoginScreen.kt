package com.example.amerikanexpress.ui.screen.login


import android.annotation.SuppressLint
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.example.amerikanexpress.R

@SuppressLint("RememberReturnType")
@Composable
fun LoginScreen(viewModel: LoginViewModel) {

    val userId by viewModel.userId.observeAsState("")
    val password by viewModel.password.observeAsState("")
    val passwordVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current

    // Ensure the context is a FragmentActivity
    val activity = context as? FragmentActivity
    if (activity == null) {
        // Display an error message to the user
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Error: This screen requires a FragmentActivity.")
            Text("Please check your app setup.")
        }
        return
    }

    // Rest of the code for biometric authentication
    val biometricManager = remember { androidx.biometric.BiometricManager.from(context) }
    val canAuthenticate = remember {
        biometricManager.canAuthenticate(androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG or androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL)
    }

    val biometricPrompt = remember {
        BiometricPrompt(
            activity,
            ContextCompat.getMainExecutor(context),
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    println("Authentication error: $errString")
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    println("Authentication succeeded!")
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    println("Authentication failed")
                }
            }
        )
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        //verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
    ) {
        Spacer(Modifier.height(40.dp))

        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .padding(16.dp)
                .height(100.dp)
        )


        Text("Good Afternoon!")

        // User ID
        Row(modifier = Modifier.padding(16.dp)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text("User Id")
                OutlinedTextField(
                    value = userId,
                    onValueChange = { viewModel.updateUseerId(it) },
                    placeholder = { Text("Enter your User ID") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // Password
        Row(modifier = Modifier.padding(16.dp)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text("Password")
                OutlinedTextField(
                    value = password,
                    onValueChange = { viewModel.updatePassword(it) },
                    placeholder = { Text("Enter your password") },
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        Icons.Default.Lock
                    }
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(start = 8.dp, end = 6.dp,)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    "Forgot User ID",
                    color = Color.Blue
                )
            }
            Text("Forgot Password?")

        }

        //Login button
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(6.dp)
        ) {
            Text(
                "Login",
                modifier = Modifier
                    .padding(8.dp)
            )
        }

        //Fingerprint
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.fingerprint),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 7.dp)
                    .size(22.dp)
            )
            Text(
                "Use Fingerprint",
                modifier = Modifier
                    .clickable {
                        if (canAuthenticate == androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS) {
                            val promptInfo = BiometricPrompt.PromptInfo.Builder()
                                .setTitle("Biometric login")
                                .setSubtitle("Log in using your biometric credential")
                                .setNegativeButtonText("Cancel")
                                .build()
                            biometricPrompt.authenticate(promptInfo)
                        } else {
                            println("Biometric authentication not available")
                        }
                    }
            )
        }

        Row(
            modifier = Modifier
                .padding(start = 8.dp, end = 4.dp,)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.shield),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 7.dp)
                        .size(25.dp)
                )
                Text("Security Tips")
            }
            TextButton(
                onClick = { },
                modifier = Modifier
            ) {
                Text(
                    "New User? Register",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }


        }

        Spacer(Modifier.height(120.dp))


        Row {
            HorizontalDivider(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }

        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                "Change Language",
            )
        }

        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            OutlinedButton(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth(0.5f),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text("English")
            }
            OutlinedButton(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth(1f),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text("Spanish")
            }
        }

        Row {
            HorizontalDivider(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }

        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Apply now
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.wrapContentWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(Color.LightGray, CircleShape)
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.registration),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                TextButton(
                    onClick = { },
                    modifier = Modifier
                ) {
                    Text(
                        "Apply now",
                        modifier = Modifier
                    ) // Add padding to separate text from image
                }
            }

            // Amex offers
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.wrapContentWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(Color.LightGray, CircleShape)
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.offer),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                TextButton(
                    onClick = { },
                    modifier = Modifier
                ) {
                    Text("Amex offers", modifier = Modifier)
                }
            }

            // Resume application
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.wrapContentWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(Color.LightGray, CircleShape)
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.resume),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                TextButton(
                    onClick = { },
                    modifier = Modifier
                ) {
                Text("Resume application",
                    modifier = Modifier)
            }
                }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            // contact us
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(end = 40.dp, )
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(Color.LightGray, CircleShape)
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.contact),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                TextButton(
                    onClick = { },
                ) {
                Text("Contact Us", modifier = Modifier)
            }
            }

            // privacy statement
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.wrapContentWidth().padding(end = 10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(Color.LightGray, CircleShape)
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.privacy),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                TextButton(
                    onClick = { },
                    modifier = Modifier
                ) {
                    Text("Privacy Statement", modifier = Modifier)
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.wrapContentWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(Color.LightGray, CircleShape)
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.terms),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                TextButton(
                    onClick = { },
                    modifier = Modifier
                ) {
                    Text("Terms of Service", modifier = Modifier)
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 45.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "© 2025 American Express Company. All rights reserved.",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
            )
        }
    }
}