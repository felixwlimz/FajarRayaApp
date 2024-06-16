package com.fajarraya.app.components.pager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fajarraya.app.components.buttons.PrimaryButton

@Composable
fun TransactionPager(modifier: Modifier = Modifier){

    Row(modifier = modifier.fillMaxSize(), horizontalArrangement = Arrangement.spacedBy(8.dp)){

        PrimaryButton(modifier = Modifier.width(150.dp), onClick = { /*TODO*/ }, buttonText = "Completed")
        PrimaryButton(modifier = Modifier.width(150.dp), onClick = { /*TODO*/ }, buttonText = "Ongoing" )

    }

}
