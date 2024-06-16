package com.fajarraya.app.pages.transactions

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fajarraya.app.components.pager.TransactionPager

@Composable
fun TransactionsPage(modifier : Modifier = Modifier){

    LazyColumn(modifier = modifier
        .fillMaxSize()
        .padding(10.dp)){

        item {

            TransactionPager()

        }



    }


}