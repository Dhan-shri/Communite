package com.dhanshri.community.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dhanshri.community.app.PostOfficeAppRouter
import com.dhanshri.community.app.Screen
import com.dhanshri.community.data.validation.Validator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

class LoginViewModel() : ViewModel(){

    private val TAG = "LoginViewModel"
    var registrationUIState = mutableStateOf(RegistrationUiState())

    var allValidationPassed = mutableStateOf(false)

    var signUpInProgress = mutableStateOf(false)

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
            is UiEvent.PrivacyPolicyCheckedClicked -> {
                registrationUIState.value = registrationUIState.value.copy(privacyPolicyAccepted = event.status)
                printState()
            }
        }
    }

    private fun signUp(){
       Log.d(TAG, "Inside signUp()")
        printState()
        createUserInFirebase(
            email = registrationUIState.value.email,
            password = registrationUIState.value.password
        )
    }

    private fun validateDataWithRules(){

        val fNameResult = Validator.validateFirstName(firstName = registrationUIState.value.firstName)
        val lNameResult = Validator.validateLastName(lastName = registrationUIState.value.lastName)
        val emailResult = Validator.validateEmail(email = registrationUIState.value.email)
        val passwordResult = Validator.validatePassword(password = registrationUIState.value.password)
        val privacyPolicyResult = Validator.validatePrivacyPolicy(statusValue = registrationUIState.value.privacyPolicyAccepted)


        Log.d(TAG, "Inside_validateDataWithRules()")
        Log.d(TAG, "firstName $fNameResult")
        Log.d(TAG, "lastName $lNameResult")
        Log.d(TAG, "Email $emailResult")
        Log.d(TAG, "password $passwordResult")
        Log.d(TAG, "privacyPolicy $privacyPolicyResult")

        registrationUIState.value = registrationUIState.value.copy(
            firstNameError = fNameResult.isValid,
            lastNameError = lNameResult.isValid,
            emailError = emailResult.isValid,
            passwordError = passwordResult.isValid,
            privacyPolicyError = privacyPolicyResult.isValid
        )


        //for checking all validation passed or not
        allValidationPassed.value = fNameResult.isValid && lNameResult.isValid && emailResult.isValid && passwordResult.isValid && privacyPolicyResult.isValid
    }
    private fun printState(){
        Log.d(TAG, "Inside printState()")
        Log.d(TAG, "Current State: ${registrationUIState.value.toString()}")
    }

    private fun createUserInFirebase(email: String, password: String){
        signUpInProgress.value = true
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "createUserWithEmail:inside_onCompleteListener")
                Log.d(TAG, "createUserWithEmail:success= ${it.isSuccessful}")

                signUpInProgress.value = false
                if (it.isSuccessful){
                    PostOfficeAppRouter.navigateTo(Screen.HomeScreen)
                }

            }
            .addOnFailureListener {
                Log.d(TAG, "createUserWithEmail:failure exception ${it.message}")
            }
    }


    fun logout(){
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()

        val authStateListener = AuthStateListener {
            if (it.currentUser == null){
                PostOfficeAppRouter.navigateTo(Screen.LoginScreen)
                Log.d(TAG, "logout: user is logged out")
            }else{
                Log.d(TAG, "logout: user is still logged in")
            }
        }

        firebaseAuth.addAuthStateListener(authStateListener)
    }

}

sealed class UiEvent{
    data class FirstNameChanged(val firstName: String): UiEvent()
    data class LastNameChanged(val lastName: String): UiEvent()
    data class EmailChanged(val email: String): UiEvent()
    data class PasswordChanged(val password: String): UiEvent()

    data class PrivacyPolicyCheckedClicked(val status: Boolean): UiEvent()
    object RegisterButtonClicked : UiEvent()


}