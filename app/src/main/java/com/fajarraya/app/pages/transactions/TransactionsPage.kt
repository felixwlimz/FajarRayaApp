package com.fajarraya.app.pages.transactions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fajarraya.app.components.cards.TransactionCard
import com.fajarraya.app.components.pager.TransactionPager
import com.fajarraya.app.utils.DummyData
import com.fajarraya.app.utils.Extensions

@Composable
fun TransactionsPage(modifier : Modifier = Modifier){

    LazyColumn(
        modifier = modifier
        .fillMaxSize()
        .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ){

        item {
            TransactionPager()
        }

        items(DummyData.transactions){
            TransactionCard(
                title = it.purchaseItem,
                quantityDesc = "${it.quantity} x ${Extensions.toRupiah(it.price)} " ,
                isCompleted = it.isCompleted
            )
        }



    }


}