package com.fajarraya.app.pages.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.fajarraya.app.components.menu.ProfileMenu
import com.fajarraya.app.R
import com.fajarraya.app.components.navigation.Screen


@Composable
fun ProfilePage(modifier: Modifier = Modifier, navHostController: NavHostController){


       Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp)) {


           ProfileMenu(text = stringResource(id = R.string.profile), onClick = {
              navHostController.navigate(Screen.Profile.ProfileDetails.route)

           })

           Divider()

           ProfileMenu(text = stringResource(id = R.string.account_security), onClick = {
               navHostController.navigate(Screen.Profile.Account.route)
           })

           Divider()

           ProfileMenu(text = stringResource(id = R.string.language)){

           }

           Divider()


           ProfileMenu(text = stringResource(id = R.string.dark_mode))


       }


}