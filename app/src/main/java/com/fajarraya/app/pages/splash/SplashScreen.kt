package com.fajarraya.app.pages.splash


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashPage(
    modifier : Modifier = Modifier,
    navHostController: NavHostController,
    splashViewModel: SplashViewModel = koinViewModel()
){

    LaunchedEffect(key1 = Unit) {
        splashViewModel.checkLogin(navHostController)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CircularProgressIndicator()

    }

}






