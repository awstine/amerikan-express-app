package com.example.amerikanexpress.ui.screen.sighnUp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.amerikanexpress.ui.screen.data.Screens
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel,
    navController: NavController
) {
    var isChecked by remember { mutableStateOf(false) }
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val confirmPassword by viewModel.confirmPassword.collectAsState()
    val signUpResult by viewModel.signUpResult.collectAsState()

    LaunchedEffect(signUpResult) {
        signUpResult?.getOrNull()?.let { success ->
            if (success) {
                navController.navigate(Screens.LoginScreen.route)
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.padding(top = 80.dp),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "Sign Up",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        Spacer(modifier = Modifier.height(35.dp))

        Column(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { viewModel.updateEmail(it) },
                    placeholder = { Text("Enter email") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { viewModel.updatePassword(it) },
                placeholder = { Text("Enter password") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(40.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { viewModel.updateConfirmPassword(it) },
                placeholder = { Text("Enter confirm password") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Error message
            signUpResult?.exceptionOrNull()?.let { error ->
                Text(text = error.message ?: "An error occurred", color = MaterialTheme.colorScheme.error)
            }
        }

        Button(
            onClick = {
                if (password == confirmPassword) {
                    viewModel.signUpUser(email, password, confirmPassword)
                } else {
                    // Handle password mismatch
                }
            },
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Sign Up",
                fontSize = 22.sp,
                modifier = Modifier.padding(10.dp)
            )
        }

        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            Text(
                text = "Already have an account?",
                fontSize = 18.sp,
                modifier = Modifier.weight(1f),
            )
            TextButton(
                onClick = { navController.navigate(Screens.LoginScreen.route) },
            ) {
                Text(
                    text = "Sign in",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 90.dp, start = 15.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it },
            )
            Text(
                text = "By signing up you have agreed to terms and conditions ",
                fontSize = 18.sp,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
