package com.fajarraya.app.pages.profile.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fajarraya.app.components.cards.UserCard
import com.fajarraya.app.components.forms.TextandInput
import com.fajarraya.app.R
import com.fajarraya.app.components.buttons.PrimaryButton

@Composable
fun ProfileDetailPage(modifier : Modifier = Modifier){

   Column(modifier = modifier
       .fillMaxSize()
       .verticalScroll(rememberScrollState())
       .padding(15.dp),
       verticalArrangement = Arrangement.spacedBy(10.dp)
   ) {

       UserCard(user = "Felix Winston", email = "felix@example.com")

       TextandInput(
           textInputTitle = stringResource(id = R.string.name),
           fieldValue = "Felix Winston",
           placeholderText = stringResource(id = R.string.placeholder_name)
       )

       TextandInput(
           textInputTitle = stringResource(id = R.string.email),
           fieldValue = "felix@example.com",
           placeholderText = stringResource(id = R.string.placeholder_email)
       )

       TextandInput(
           textInputTitle = stringResource(id = R.string.phone_number),
           fieldValue = "08123456789",
           placeholderText = stringResource(id = R.string.placeholder_phone)
       )

       PrimaryButton(onClick = {

       }, buttonText = stringResource(id = R.string.save) )




   }

}