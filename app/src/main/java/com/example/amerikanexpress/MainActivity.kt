package com.example.amerikanexpress


import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.amerikanexpress.ui.screen.login.LoginScreen
import com.example.amerikanexpress.ui.screen.login.LoginViewModel
import com.example.amerikanexpress.ui.screen.sighnUp.SignUpScreen
import com.example.amerikanexpress.ui.screen.sighnUp.SignUpViewModel


class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   // LoginScreen(viewModel = LoginViewModel())
                    SignUpScreen(viewModel = SignUpViewModel())
                }
            }
        }
    }
}