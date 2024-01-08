package com.dhanshri.community.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel() : ViewModel(){

    private val TAG = "LoginViewModel"
    private var registrationUIState = mutableStateOf(RegistrationUiState())

    fun onEvent(event: UiEvent){
        when(event){
            is UiEvent.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(firstName = event.firstName)
                printState()
            }
            is UiEvent.LastNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(lastName = event.lastName)
                printState()
            }
            is UiEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(email = event.email)
                printState()
            }
            is UiEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(password = event.password)
                printState()
            }
            is UiEvent.RegisterButtonClicked -> {
                printState()
                signUp()
            }
        }
    }

    private fun signUp(){
       Log.d(TAG, "Inside signUp()")
        printState()
    }
    private fun printState(){
        Log.d(TAG, "Inside printState()")
        Log.d(TAG, "Current State: ${registrationUIState.value.toString()}")
    }

}

sealed class UiEvent{
    data class FirstNameChanged(val firstName: String): UiEvent()
    data class LastNameChanged(val lastName: String): UiEvent()
    data class EmailChanged(val email: String): UiEvent()
    data class PasswordChanged(val password: String): UiEvent()
    object RegisterButtonClicked : UiEvent()

}