package com.dhanshri.community.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.dhanshri.community.R
import com.dhanshri.community.app.PostOfficeAppRouter
import com.dhanshri.community.app.Screen
import com.dhanshri.community.components.ButtonComponent

@Composable
fun SplashScreen() {

    Surface (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(28.dp)
    ){
        LottieWelcome()
    }
}

@Composable
fun LottieWelcome(){
    val isPlaying by remember { mutableStateOf(true) }

    val speed by remember {
        mutableStateOf(1f)
    }

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.welcome_lottie)
    )

    val progress by animateLottieCompositionAsState(
        composition =  composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlaying,
        speed = speed,
        restartOnPlay = false
    )

    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){


        LottieAnimation(composition = composition, progress = progress,
            Modifier
                .height(400.dp)
                .fillMaxWidth()
                .padding(40.dp)
        )

        Spacer(modifier = Modifier.height(90.dp))
        ButtonComponent(value = "Get Started", onButtonSelected = {
            PostOfficeAppRouter.navigateTo(Screen.SignUpScreen)
        })

    }

}

