package com.example.amerikanexpress


import HomeScreen
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.amerikanexpress.ui.screen.OtpScreen.OTPScreen
import com.example.amerikanexpress.ui.screen.login.LoginScreen
import com.example.amerikanexpress.ui.screen.login.LoginViewModel
import com.example.amerikanexpress.ui.screen.sighnUp.SignUpScreen
import com.example.amerikanexpress.ui.screen.sighnUp.SignUpViewModel
import com.google.firebase.FirebaseApp


class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                FirebaseApp.initializeApp(this)

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "register"
                ) {
                    composable("register") {
                        SignUpScreen(viewModel = SignUpViewModel(), navController)

                    }
                    composable("login") {
                        LoginScreen(viewModel = LoginViewModel(), navController)
                    }
                    composable("otp") {
                        OTPScreen(navController)
                    }
                    composable("profile"){navController}
                    composable("home"){
                        HomeScreen()
                    }
                }
            }
        }
    }
}