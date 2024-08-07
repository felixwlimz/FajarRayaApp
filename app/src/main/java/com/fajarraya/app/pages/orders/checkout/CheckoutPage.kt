package com.fajarraya.app.pages.orders.checkout

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.fajarraya.app.components.cards.CheckoutCard
import com.fajarraya.app.components.forms.TextAndRadioButton
import com.fajarraya.app.components.navigation.Screen
import com.fajarraya.app.constants.WidgetConstants
import com.fajarraya.app.utils.Extensions
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.androidx.compose.koinViewModel

@SuppressLint("CheckResult")
@Composable
fun CheckoutPage(
    modifier: Modifier = Modifier,
    checkoutViewModel: CheckoutViewModel = koinViewModel(),
    navHostController: NavHostController
) {

    val carts = checkoutViewModel.cartItems.observeAsState()


    var selectedOption by remember {
        mutableStateOf("")
    }


    val totalPrice = remember {
        derivedStateOf {
            var total = 0L
            if (carts.value !== null)
                for (item in carts.value!!) {
                    total += item.quantity * item.harga
                }
            total
        }
    }

    LaunchedEffect(Unit) {
        checkoutViewModel.subscribeProduct()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 0.dp)
            .verticalScroll(rememberScrollState())
    ) {


        if (carts.value !== null)
            for (cart in carts.value!!) {
                CheckoutCard(
                    productName = cart.nama,
                    imageUrl = cart.gambar,
                    quantity = cart.quantity,
                    price = cart.harga,
                )
            }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            "Select Payment Method",
            fontWeight = FontWeight(WidgetConstants.FONT_WEIGHT_SEMI),
            fontSize = WidgetConstants.HEADER_FONT_SIZE.sp
        )


        TextAndRadioButton(
            selectedOption = "Cash",
            onSelect = {
                    selectedOption = "Cash"
                },
            isSelected = selectedOption == "Cash"
            )



        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                "Total Price",
                fontWeight = FontWeight(WidgetConstants.FONT_WEIGHT_SEMI),
                fontSize = WidgetConstants.HEADER_FONT_SIZE.sp
            )

            Text(
                Extensions.toRupiah(totalPrice.value),
                fontSize = WidgetConstants.HEADER_FONT_SIZE.sp
            )

        }
        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                checkoutViewModel.addTransaction(
                    selectedOption,
                    totalPrice.value
                )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        navHostController.navigate(Screen.Orders.PrintReceiptPage.route + "/${it}")
                    },
                        {
                            it.printStackTrace()
                        })
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            Text("Place Order")
        }


        Spacer(modifier = Modifier.height(10.dp))

    }

}