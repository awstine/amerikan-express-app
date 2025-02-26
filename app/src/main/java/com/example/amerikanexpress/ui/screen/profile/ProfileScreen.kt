package com.example.amerikanexpress.ui.screen.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.amerikanexpress.ui.screen.data.Screens
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileScreen(
    navController: NavController
) {
    val auth = FirebaseAuth.getInstance()
    var showDialog by remember { mutableStateOf(false) }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
        ) {
            IconButton(
                onClick = { navController.navigate(Screens.HomeScreen.route) }
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Welcome profile screen",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(40.dp))

        Button(
            onClick = { showDialog = true},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Sign Out",
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }

    if (showDialog){
        AlertDialog(
            title = { Text("Sign Out")},
            onDismissRequest = {showDialog = false},
            text = { Text("Are sure you want to Sign out",
                fontSize = 16.sp,
                textAlign = TextAlign.Start)
                   },
            confirmButton = {
                Button(
                    onClick = {
                        auth.signOut()
                        navController.navigate(Screens.LoginScreen.route)
                        showDialog = false
                    }
                ) {
                    Text("Yes")
                }
            },
            dismissButton = {
                Button(
                    onClick = {showDialog = false }
                ) {
                    Text("No")
                }
            }
        )
    }
}