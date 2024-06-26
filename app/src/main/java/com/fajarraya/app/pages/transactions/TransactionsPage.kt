package com.fajarraya.app.pages.transactions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fajarraya.app.components.cards.TransactionCard
import com.fajarraya.app.components.pager.TransactionPager
import com.fajarraya.app.utils.DummyData
import com.fajarraya.app.utils.Extensions
import org.koin.androidx.compose.koinViewModel

@Composable
fun TransactionsPage(
    modifier: Modifier = Modifier,
    transactionsViewModel: TransactionsViewModel = koinViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        transactionsViewModel.subscribeTransactions()
    }

    val transactions = transactionsViewModel.transactions.observeAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {

        item {
            TransactionPager()
        }

        if(transactions.value !== null){
            items(transactions.value!!) {
                TransactionCard(
                    transaction = it
                )
            }
        }


    }


}