package com.fajarraya.app.pages.auth.register

import android.app.Activity
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fajarraya.app.R
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.fajarraya.app.MainActivity
import com.fajarraya.app.components.buttons.PrimaryButton
import com.fajarraya.app.components.forms.TextandInput
import com.fajarraya.app.components.navigation.AuthScreen
import com.fajarraya.app.components.navigation.Screen
import com.fajarraya.app.constants.WidgetConstants
import com.fajarraya.app.models.UserType
import com.fajarraya.app.ui.theme.PrimaryBlue
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterPage(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    registerViewModel: RegisterViewModel = koinViewModel()
){

    val context = LocalContext.current

    val name by remember {
        derivedStateOf { registerViewModel.nameInput }
    }
    val username by remember {
        derivedStateOf { registerViewModel.usernameInput }
    }
    val email by remember {
        derivedStateOf { registerViewModel.emailInput }
    }
    val password by remember {
        derivedStateOf { registerViewModel.passwordInput }
    }
    val isError by remember {
        derivedStateOf { registerViewModel.isError }
    }
    val errorText by remember {
        derivedStateOf { registerViewModel.errorText }
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = R.string.register),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(WidgetConstants.FONT_WEIGHT_BOLD),
            fontSize = WidgetConstants.XL_FONT_SIZE.sp,
        )

        Spacer(modifier = Modifier.padding(5.dp))
        TextandInput(textInputTitle = stringResource(id = R.string.placeholder_name),
            fieldValue = name,
            placeholderText = stringResource(id = R.string.placeholder_name),
            onValueChange = registerViewModel::setName,
        )
        Spacer(modifier = Modifier.padding(5.dp))
        TextandInput(textInputTitle = stringResource(id = R.string.placeholder_username),
            fieldValue = username,
            placeholderText = stringResource(id = R.string.placeholder_username),
            onValueChange = registerViewModel::setUsername,
        )

        Spacer(modifier = Modifier.padding(5.dp))


        TextandInput(textInputTitle = stringResource(id = R.string.placeholder_email),
            fieldValue = email,
            placeholderText = stringResource(id = R.string.placeholder_email),
            onValueChange = registerViewModel::setEmail,
        )

        Spacer(modifier = Modifier.padding(5.dp))

        TextandInput(textInputTitle = stringResource(id = R.string.placeholder_password),
            fieldValue = password,
            placeholderText = stringResource(id = R.string.placeholder_password),
            onValueChange = registerViewModel::setPassword,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Send
            )
        )

        Box(contentAlignment = Alignment.CenterEnd,  modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)){
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp)) {
                Text(text = "Have an account")
                TextButton(onClick = { navHostController.navigate(AuthScreen.Login.route) }) {
                    Text(text = stringResource(id = R.string.login),  color = PrimaryBlue)
                }
            }
        }


        Spacer(modifier = Modifier.padding(15.dp))

        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = {
                registerViewModel.validateRegister(name, username, email, password, UserType.ADMIN){
                    navHostController.navigate(Screen.Login.route)
                }
            }, buttonText = stringResource(id = R.string.register) )

        Text(errorText)

    }
}