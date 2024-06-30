package com.fajarraya.app.pages.orders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fajarraya.app.components.cards.OrderCard
import com.fajarraya.app.constants.WidgetConstants
import org.koin.androidx.compose.koinViewModel


@Composable
fun OrdersPage(modifier: Modifier = Modifier, orderViewModel: OrderViewModel = koinViewModel()){


    val products = orderViewModel.productList.observeAsState()

    LazyColumn(modifier = modifier
            .fillMaxSize()
            .padding(10.dp), verticalArrangement = Arrangement.spacedBy(20.dp)){

        item {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = TextFieldValue(""),
                shape = RoundedCornerShape(20.dp),
                onValueChange = {},
                placeholder = {
                    Text(text = "Search For Products", fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp)
                },
            )
        }

            if(products.value != null){
                items(products.value!!){
                    OrderCard(text = it.namaBarang, imageUrl=it.gambarProduk, onButtonClick = {})
                }
            }



        }


}