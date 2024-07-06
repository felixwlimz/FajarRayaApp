package com.fajarraya.app.pages.profile.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fajarraya.app.components.cards.UserCard
import com.fajarraya.app.components.forms.TextandInput
import com.fajarraya.app.R
import com.fajarraya.app.components.buttons.PrimaryButton
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileDetailPage(
    modifier: Modifier = Modifier,
    profileDetailViewModel: ProfileDetailViewModel = koinViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        profileDetailViewModel.getDetail()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        UserCard(user = profileDetailViewModel.userdata.name, email = profileDetailViewModel.userdata.email)

        TextandInput(
            textInputTitle = stringResource(id = R.string.name),
            fieldValue = profileDetailViewModel.userdata.name,
            placeholderText = stringResource(id = R.string.placeholder_name)
        )

        TextandInput(
            textInputTitle = stringResource(id = R.string.email),
            fieldValue = profileDetailViewModel.userdata.email,
            placeholderText = stringResource(id = R.string.placeholder_email)
        )

        TextandInput(
            textInputTitle = stringResource(id = R.string.phone_number),
            fieldValue = "08123456789",
            placeholderText = stringResource(id = R.string.placeholder_phone)
        )

        Text(
            text = "Kamu Login sebagai ${profileDetailViewModel.userdata.superAdmin.name}",

        )


        PrimaryButton(onClick = {

        }, buttonText = stringResource(id = R.string.save))


    }

}