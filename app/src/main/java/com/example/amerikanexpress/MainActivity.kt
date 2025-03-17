package com.example.amerikanexpress


import HomeScreen
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.amerikanexpress.ui.screen.OtpScreen.OTPScreen
import com.example.amerikanexpress.ui.screen.profile.ProfileScreen
import com.example.amerikanexpress.ui.screen.login.LoginScreen
import com.example.amerikanexpress.ui.screen.login.LoginViewModel
import com.example.amerikanexpress.ui.screen.sighnUp.SignUpScreen
import com.example.amerikanexpress.ui.screen.sighnUp.SignUpViewModel
import com.example.amerikanexpress.ui.screen.splash.SplashScreen
import com.example.amerikanexpress.ui.screen.splash.SplashScreenViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth


class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                FirebaseApp.initializeApp(this)

                val navController = rememberNavController()
                val context = LocalContext.current
                val user = FirebaseAuth.getInstance().currentUser

                val startDestination = if(user != null)"home" else "login"

                NavHost(
                    navController = navController,
                    startDestination = startDestination
                ) {
                    composable("register") {
                        SignUpScreen(viewModel = SignUpViewModel(), navController)

                    }
                    composable("login") {
                        LoginScreen(viewModel = LoginViewModel(context), navController)
                    }
                    composable("otp") {
                        OTPScreen(navController)
                    }
                    composable("profile"){
                        ProfileScreen(navController)
                    }
                    composable("home"){
                        HomeScreen(navController)
                    }
                    composable("splash"){
                        SplashScreen(navController, SplashScreenViewModel(context))
                    }
                }
            }
        }
    }
}