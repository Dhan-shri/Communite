package com.dhanshri.community.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dhanshri.community.R
import com.dhanshri.community.app.PostOfficeAppRouter
import com.dhanshri.community.app.Screen
import com.dhanshri.community.app.SystemBackButtonHandler
import com.dhanshri.community.components.ButtonComponent
import com.dhanshri.community.components.ClickableLoginTextComponent
import com.dhanshri.community.components.DividerTextComponent
import com.dhanshri.community.components.HeadingTextComponents
import com.dhanshri.community.components.MyTextFieldsComponent
import com.dhanshri.community.components.NormalTextComponents
import com.dhanshri.community.components.PasswordTextFieldsComponent
import com.dhanshri.community.components.UnderLinedTextComponent
import com.dhanshri.community.data.LoginViewModel
import com.dhanshri.community.data.loginpage.LoginPageViewmodel
import com.dhanshri.community.data.loginpage.LoginUIEvent

@Composable
fun LoginScreen(loginViewModel: LoginPageViewmodel = viewModel()) {

    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Surface (modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                NormalTextComponents(value = stringResource(R.string.login))
                HeadingTextComponents(value = stringResource(R.string.welcome_back))

                Spacer(modifier = Modifier.height(20.dp))
                MyTextFieldsComponent(labelValue = stringResource(id = R.string.email),
                    painterResource = painterResource(id = R.drawable.ic_mail),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.emailError
                )

                PasswordTextFieldsComponent(labelValue = stringResource(id = R.string.password), painterResource = painterResource(id = R.drawable.ic_moder),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.passwordError
                )
                Spacer(modifier = Modifier.height(40.dp))

                UnderLinedTextComponent(value = "Forgot your Password?")

                Spacer(modifier = Modifier.height(50.dp))

                ButtonComponent(value = "Login",
                    onButtonClicked = {
                        loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                    },
                    isEnabled = loginViewModel.allValidationPassed.value
                )

                Spacer(modifier = Modifier.height(20.dp))
                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected ={
                    PostOfficeAppRouter.navigateTo(Screen.SignUpScreen)
                } )
            }

        }
        if (loginViewModel.loginInProgress.value){
            CircularProgressIndicator()
        }
    }


    SystemBackButtonHandler {
        PostOfficeAppRouter.navigateTo(Screen.SignUpScreen)
    }
}



@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}