package com.example.weatherapp

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.edit


@Composable
fun rememberThemeState(): MutableState<Boolean> {
    val context = LocalContext.current
    val isDarkTheme = context.getSharedPreferences("theme", Context.MODE_PRIVATE)
        .getBoolean("isDarkTheme", false)
    return remember { mutableStateOf(isDarkTheme) }
}

private const val THEME_PREFS_NAME = "theme"
private const val IS_DARK_THEME_KEY = "isDarkTheme"

fun saveThemeState(context: Context, isDarkTheme: Boolean) {
    val prefs = context.getSharedPreferences(THEME_PREFS_NAME, Context.MODE_PRIVATE)
    prefs.edit {
        putBoolean(IS_DARK_THEME_KEY, isDarkTheme)
    }
}

fun getThemeState(context: Context): Boolean {
    val sharedPreferences = context.getSharedPreferences("theme", Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean("isDarkTheme", false)
}