package com.dhanshri.community.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dhanshri.community.data.validation.Validator

class LoginViewModel() : ViewModel(){

    private val TAG = "LoginViewModel"
    var registrationUIState = mutableStateOf(RegistrationUiState())

    fun onEvent(event: UiEvent){
        validateDataWithRules() // change the ui filed at a time of adding data
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
        validateDataWithRules()
    }

    private fun validateDataWithRules(){
        val fNameResult = Validator.validateFirstName(firstName = registrationUIState.value.firstName)
        val lNameResult = Validator.validateLastName(lastName = registrationUIState.value.lastName)
        val emailResult = Validator.validateEmail(email = registrationUIState.value.email)
        val passwordResult = Validator.validatePassword(password = registrationUIState.value.password)

        Log.d(TAG, "Inside_validateDataWithRules()")
        Log.d(TAG, "firstName $fNameResult")
        Log.d(TAG, "lastName $lNameResult")
        Log.d(TAG, "Email $emailResult")
        Log.d(TAG, "password $passwordResult")

        registrationUIState.value = registrationUIState.value.copy(
            firstNameError = fNameResult.isValid,
            lastNameError = lNameResult.isValid,
            emailError = emailResult.isValid,
            passwordError = passwordResult.isValid,
        )
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