package com.example.amerikanexpress.ui.screen.login

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.datastore by preferencesDataStore("user_prefs")

class UserPreference(private val context: Context){
    companion object{
        private val LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")
        private val USER_IR_KEY = stringPreferencesKey("user_id")
    }

    val isLoggedIn: Flow<Boolean> = context.datastore.data.map { preferences ->
        preferences[LOGGED_IN_KEY]?:false
    }

    suspend fun saveLoginState(isLoggedIn: Boolean){
        context.datastore.edit { preferences ->
            preferences[LOGGED_IN_KEY] = isLoggedIn
        }
    }

    suspend fun saveUserId(userId: String) {
        context.datastore.edit { preferences ->
            preferences[USER_IR_KEY] = userId
        }
    }
}