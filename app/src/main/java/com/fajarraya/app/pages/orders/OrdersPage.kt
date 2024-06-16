package com.fajarraya.app.pages.orders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.fajarraya.app.components.cards.OrderCard
import com.fajarraya.app.utils.OrderViewModelFactory


@Composable
fun OrdersPage(modifier: Modifier = Modifier){


    val factory = OrderViewModelFactory.getInstance(LocalContext.current)
    val viewModelStoreOwner = LocalViewModelStoreOwner.current!!
    val orderViewModel = ViewModelProvider(viewModelStoreOwner,factory)[OrderViewModel::class.java]
    val products = orderViewModel.productList.observeAsState()

        LazyColumn(modifier = modifier
            .fillMaxSize()
            .padding(10.dp), verticalArrangement = Arrangement.spacedBy(20.dp)){

            if(products.value != null){
                items(products.value!!){
                    OrderCard(text = it.namaBarang, onButtonClick = {})
                }
            }



        }


}