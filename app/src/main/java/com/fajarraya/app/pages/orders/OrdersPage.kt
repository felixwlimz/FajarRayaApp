package com.fajarraya.app.pages.orders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.fajarraya.app.components.cards.OrderCard
import com.fajarraya.app.components.navigation.Screen
import com.fajarraya.app.constants.WidgetConstants
import com.fajarraya.app.utils.Extensions
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.androidx.compose.koinViewModel


@Composable
fun OrdersPage(
    modifier: Modifier = Modifier,
    orderViewModel: OrderViewModel = koinViewModel(),
    navHostController: NavHostController
) {

    val cart = orderViewModel.cartItems.observeAsState()

    val products = orderViewModel.productList.observeAsState()

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

    Column{
        LazyColumn(
            modifier = modifier
                .weight(1f)
                .padding(10.dp), verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            item {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = TextFieldValue(""),
                    shape = RoundedCornerShape(20.dp),
                    onValueChange = {},
                    placeholder = {
                        Text(
                            text = "Search For Products",
                            fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp
                        )
                    },
                )
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
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            {
                                navHostController.navigate(Screen.Orders.CheckoutPage.route)
                            },
                            {
                                it.printStackTrace()
                            }
                        );
//                navHostController.navigate(Screen.Checkout.route)

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