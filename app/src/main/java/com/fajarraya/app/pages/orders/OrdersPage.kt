package com.fajarraya.app.pages.orders

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.fajarraya.app.components.cards.OrderCard
import com.fajarraya.app.components.dropdown.Dropdown
import com.fajarraya.app.components.navigation.Screen
import com.fajarraya.app.constants.WidgetConstants
import com.fajarraya.app.models.SortType
import com.fajarraya.app.utils.Extensions
import org.koin.androidx.compose.koinViewModel


@SuppressLint("CheckResult")
@Composable
fun OrdersPage(
    modifier: Modifier = Modifier,
    orderViewModel: OrderViewModel = koinViewModel(),
    navHostController: NavHostController
) {

    val cart = orderViewModel.cartItems.observeAsState()

    val products = orderViewModel.productList.observeAsState()

    Log.d("products", products.toString())


    val totalCart = remember{
        derivedStateOf {
            var total =  0
            for (item in cart.value!!) {
                total += item.quantity
            }
            total
        }
    }

    val totalPrice = remember{
        derivedStateOf {
            var total =  0L
            for (item in cart.value!!) {
                total += item.quantity * item.harga
            }
            total
        }
    }



    var sortType by remember {
        mutableStateOf(SortType.ASCENDING)
    }

    var searchText by remember {
        mutableStateOf("")
    }

    LaunchedEffect(products) {
        orderViewModel.getAllProducts(sortType)
    }


    Column{
        LazyColumn(
            modifier = modifier
                .weight(1f)
                .padding(10.dp), verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            item {
               Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                   OutlinedTextField(
                       modifier = Modifier.width(340.dp),
                       value = TextFieldValue(searchText),
                       shape = RoundedCornerShape(20.dp),
                       onValueChange = {
                           searchText = it.text
                           orderViewModel.searchProduct(searchText)
                       },
                       placeholder = {
                           Text(
                               text = "Search For Products",
                               fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp
                           )
                       },
                   )

                   Dropdown {
                       DropdownMenuItem(text = {
                           Text(
                               text = "Sort by Descending ( A - Z )"
                           )
                       }, onClick = {
                           sortType = SortType.DESCENDING
                           orderViewModel.getAllProducts(sortType)
                       })

                       DropdownMenuItem(text = { Text(
                           text = "Sort by Ascending ( Z - A )"
                       )}, onClick = {
                           sortType = SortType.ASCENDING
                           orderViewModel.getAllProducts(sortType)
                       })
                   }

               }

            }

            if (products.value != null) {
                items(products.value!!) {
                    OrderCard(
                        text = it.namaBarang,
                        imageUrl = it.gambarProduk,
                        hargaproduk = it.hargaProduk,
                        onAddClick = {
                            orderViewModel.addProductToCart(it)
                        },
                        onRemoveClick = {
                            orderViewModel.reduceProductQuantity(it)
                        },
                        quantity = cart.value?.find { item -> item.kodeBarang == it.kodeBarang }?.quantity
                            ?: 0,
                    )
                }
            }


        }

        if(totalCart.value > 0){
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                onClick = {
                    orderViewModel.checkoutCart()
                        .subscribe(
                            {
                                navHostController.navigate(Screen.Orders.CheckoutPage.route)
                            },
                            {
                                it.printStackTrace()
                            }
                        )
                }
            ){
                Row(
                    modifier =
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text("Checkout ${totalCart.value} items")
                    Text(Extensions.toRupiah(totalPrice.value))
                }
            }
        }

    }

}