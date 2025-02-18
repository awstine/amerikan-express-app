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

@Composable
//@Preview
fun SignUpScreen(
    viewModel: SignUpViewModel,
    navController: NavController
) {

    var isChecked by remember { mutableStateOf(false) }
    val email by viewModel.email.observeAsState("")
    val password by viewModel.password.observeAsState("")
    val confirmPassword by viewModel.confirmPassword.observeAsState("")


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
        }

        Button(
            onClick = { navController.navigate(Screens.OtpScreen.route) },
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