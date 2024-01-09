package com.dhanshri.community.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dhanshri.community.R
import com.dhanshri.community.components.ButtonComponent
import com.dhanshri.community.components.HeadingTextComponents
import com.dhanshri.community.data.LoginViewModel

@Composable
fun HomeScreen(loginViewModel: LoginViewModel = viewModel()){
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(28.dp)
    ) {
        Column (modifier = Modifier.fillMaxSize()) {
            HeadingTextComponents(value = "Home Screen")
            
            ButtonComponent(value = stringResource(R.string.logout) , onButtonClicked = {
                loginViewModel.logout()
            },
                isEnabled = true
            )
        }
    }
}



@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}