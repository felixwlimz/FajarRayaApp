package com.fajarraya.app.pages.profile.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.fajarraya.app.R
import com.fajarraya.app.components.menu.AccountMenu
import com.fajarraya.app.components.navigation.Screen

@Composable
fun AccountPage(modifier: Modifier = Modifier, navHostController: NavHostController){
    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp)) {


        AccountMenu(text = stringResource(id = R.string.two_step))

        Divider()

        AccountMenu(text = stringResource(id = R.string.change_password), onClick = {
            navHostController.navigate(Screen.Profile.Account.route)
        })

        Divider()

        AccountMenu(text = stringResource(id = R.string.trusted_devices)){

        }



    }


}