package com.fajarraya.app.pages.auth.login

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.fajarraya.app.R
import com.fajarraya.app.MainActivity
import com.fajarraya.app.components.buttons.PrimaryButton
import com.fajarraya.app.components.forms.TextandInput
import com.fajarraya.app.components.navigation.AuthScreen
import com.fajarraya.app.constants.WidgetConstants
import com.fajarraya.app.ui.theme.PrimaryBlue

@Composable
fun LoginPage(
    modifier : Modifier = Modifier,
    navHostController: NavHostController,
    loginViewModel: LoginViewModel = viewModel()
){

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = R.string.login),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(WidgetConstants.FONT_WEIGHT_BOLD),
            fontSize = WidgetConstants.XL_FONT_SIZE.sp
        )
        Spacer(modifier = Modifier.padding(15.dp))

        TextandInput(textInputTitle = stringResource(id = R.string.placeholder_name),
            fieldValue = loginViewModel.username.value,
            placeholderText = stringResource(id = R.string.placeholder_name),
            onValueChange = loginViewModel::setUsername
        )

        Spacer(modifier = Modifier.padding(15.dp))


        TextandInput(textInputTitle = stringResource(id = R.string.placeholder_password),
            fieldValue = loginViewModel.password.value,
            placeholderText = stringResource(id = R.string.placeholder_password),
            onValueChange = loginViewModel::setPassword
        )

        Spacer(modifier = Modifier.padding(5.dp))

        Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)){
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)) {
                Text(text = "Don't have an account")
                TextButton(onClick = { navHostController.navigate(AuthScreen.Register.route)},
                 ) {
                    Text(text = stringResource(id = R.string.register), color = PrimaryBlue)
                }
            }
        }


        Spacer(modifier = Modifier.padding(5.dp))


        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = {
               context.startActivity(Intent(context, MainActivity::class.java))
        }, buttonText =  stringResource(id = R.string.login))

    }

}






