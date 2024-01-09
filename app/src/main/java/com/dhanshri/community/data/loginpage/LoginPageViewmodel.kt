package com.dhanshri.community.data.loginpage

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dhanshri.community.app.PostOfficeAppRouter
import com.dhanshri.community.app.Screen
import com.dhanshri.community.data.validation.Validator
import com.google.firebase.auth.FirebaseAuth

class LoginPageViewmodel() : ViewModel(){
    private val TAG = "LoginPageViewmodel"

    var loginUIState = mutableStateOf(LoginUIState())

    var allValidationPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

    fun onEvent(event: LoginUIEvent){
        validateLoginUIDataWithRules() // change the ui filed at a time of adding data
        when(event){
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(email = event.email)
            }
            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(password = event.password)
            }
            is LoginUIEvent.LoginButtonClicked -> {
//                printState()
                login()
            }
        }
    }

    private fun validateLoginUIDataWithRules() {
        val emailResult = Validator.validateEmail(email = loginUIState.value.email)
        val passwordResult = Validator.validatePassword(password = loginUIState.value.password)

        loginUIState.value = loginUIState.value.copy(
            emailError = emailResult.isValid,
            passwordError = passwordResult.isValid
        )

        allValidationPassed.value = emailResult.isValid && passwordResult.isValid

    }

    private fun login() {

        loginInProgress.value = true
       val email = loginUIState.value.email
       val password =  loginUIState.value.password
        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "login: ${it.result}")
                loginInProgress.value = false
                if (it.isSuccessful){
                    PostOfficeAppRouter.navigateTo(Screen.HomeScreen)
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "login: ${it.message}")
            }

    }
}