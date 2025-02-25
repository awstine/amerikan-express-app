package com.example.amerikanexpress.ui.screen.data

sealed class Screens (val route: String){
    object SignUpScreen : Screens("register")
    object LoginScreen : Screens("login")
    object OtpScreen : Screens("otp")
    object HomeScreen : Screens("home")
    object ProfileScreen : Screens("profile")
}