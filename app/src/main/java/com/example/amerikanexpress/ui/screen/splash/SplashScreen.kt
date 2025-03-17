package com.example.amerikanexpress.ui.screen.splash

import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.amerikanexpress.ui.screen.data.Screens

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashScreenViewModel
) {
    val isLoggedIn = viewModel.isLoggedIn
    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn == true) {
            navController.navigate(Screens.HomeScreen.route) {
                popUpTo(0) // Clear backstack
            }
        } else {
            navController.navigate(Screens.LoginScreen.route)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()

    }
}