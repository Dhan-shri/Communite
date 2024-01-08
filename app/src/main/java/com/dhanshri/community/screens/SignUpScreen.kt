package com.dhanshri.community.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dhanshri.community.R
import com.dhanshri.community.app.PostOfficeAppRouter
import com.dhanshri.community.app.Screen
import com.dhanshri.community.app.SystemBackButtonHandler
import com.dhanshri.community.components.ButtonComponent
import com.dhanshri.community.components.CheckBoxComponent
import com.dhanshri.community.components.ClickableLoginTextComponent
import com.dhanshri.community.components.DividerTextComponent
import com.dhanshri.community.components.HeadingTextComponents
import com.dhanshri.community.components.MyTextFieldsComponent
import com.dhanshri.community.components.NormalTextComponents
import com.dhanshri.community.components.PasswordTextFieldsComponent
import com.dhanshri.community.data.LoginViewModel
import com.dhanshri.community.data.UiEvent

@Composable
fun SignUpScreen(loginViewModel: LoginViewModel = viewModel()) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NormalTextComponents(value = stringResource(R.string.hey_there))
            HeadingTextComponents(value = stringResource(id = R.string.create_account))

            Spacer(modifier = Modifier.height(20.dp))
            MyTextFieldsComponent(
                labelValue = stringResource(id = R.string.first_name),
                painterResource(R.drawable.ic_profile),
                onTextSelected = {
                    loginViewModel.onEvent(UiEvent.FirstNameChanged(it))
                },
                errorStatus = loginViewModel.registrationUIState.value.firstNameError
            )
            MyTextFieldsComponent(
                labelValue = stringResource(id = R.string.last_name),
                painterResource(R.drawable.ic_profile),
                onTextSelected = {
                    loginViewModel.onEvent(UiEvent.LastNameChanged(it))
                },
                errorStatus = loginViewModel.registrationUIState.value.lastNameError
            )
            MyTextFieldsComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource(R.drawable.ic_mail),
                onTextSelected = {
                    loginViewModel.onEvent(UiEvent.EmailChanged(it))
                },
                errorStatus = loginViewModel.registrationUIState.value.emailError
            )
            PasswordTextFieldsComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource(R.drawable.ic_moder),
                onTextSelected = {
                    loginViewModel.onEvent(UiEvent.PasswordChanged(it))
                },
                errorStatus = loginViewModel.registrationUIState.value.passwordError
            )

            CheckBoxComponent(value = stringResource(R.string.term_condition),
                onTextSelected = {
                    PostOfficeAppRouter.navigateTo(Screen.TermsAndConditionScreen)
                })

            Spacer(modifier = Modifier.height(90.dp))
            ButtonComponent(
                value = stringResource(id = R.string.register),
                onButtonClicked = {
                    loginViewModel.onEvent(UiEvent.RegisterButtonClicked)
                },
                isEnabled = true
                )

            DividerTextComponent()

            Spacer(modifier = Modifier.height(40.dp))
            ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                PostOfficeAppRouter.navigateTo(Screen.LoginScreen)
            })

        }
    }


    SystemBackButtonHandler {
        PostOfficeAppRouter.navigateTo(Screen.SplashScreen)
    }
}

@Preview
@Composable
fun PreviewSignUpScreen() {
    SignUpScreen()
}