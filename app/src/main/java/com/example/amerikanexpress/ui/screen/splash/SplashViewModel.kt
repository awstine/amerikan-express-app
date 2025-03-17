package com.example.amerikanexpress.ui.screen.splash

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amerikanexpress.ui.screen.login.UserPreference
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class SplashScreenViewModel(context: Context) : ViewModel() {
    private val userPreferences = UserPreference(context)

    var isLoggedIn by mutableStateOf<Boolean?>(null)
        private set

    init {
        viewModelScope.launch {
            isLoggedIn = userPreferences.isLoggedIn.first()
        }
    }
}