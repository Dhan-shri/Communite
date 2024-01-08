package com.dhanshri.community.screens

import android.widget.Toast
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

@Composable
fun LoginScreen() {
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

                }
            )

            PasswordTextFieldsComponent(labelValue = stringResource(id = R.string.password), painterResource = painterResource(id = R.drawable.ic_moder),
                onTextSelected = {

                }
            )
            Spacer(modifier = Modifier.height(40.dp))

            UnderLinedTextComponent(value = "Forgot your Password?")

            Spacer(modifier = Modifier.height(50.dp))

            ButtonComponent(value = "Login",
                onButtonClicked = {

                },
                isEnabled = true
            )

            Spacer(modifier = Modifier.height(20.dp))
            DividerTextComponent()

            ClickableLoginTextComponent(tryingToLogin = false, onTextSelected ={
                PostOfficeAppRouter.navigateTo(Screen.SignUpScreen)
            } )
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