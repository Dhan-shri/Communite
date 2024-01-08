package com.dhanshri.community.app

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen{
    object SignUpScreen : Screen()
    object TermsAndConditionScreen : Screen()
    object SplashScreen : Screen()
    object LoginScreen : Screen()
}

object PostOfficeAppRouter {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.SplashScreen)

    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }
}