package com.fajarraya.app.pages.orders.checkout

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fajarraya.app.components.cards.ProductCard
import com.fajarraya.app.components.forms.TextAndRadioButton
import org.koin.androidx.compose.koinViewModel

@Composable
fun CheckoutPage(
    modifier : Modifier = Modifier,
    productId : String? = null,
    checkoutViewModel: CheckoutViewModel = koinViewModel()
){



    val paymentMethods = listOf("QRIS", "Debit/Credit Card", "E-Money", "Cash")
    var selectedOption by remember {
        mutableStateOf("")
    }
    var isSelected by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(productId) {
        if(productId != null){
            checkoutViewModel.getProduct(productId)
        }
    }

    Column(modifier = modifier
        .fillMaxSize()
        .padding(10.dp)
        .scrollable(rememberScrollState(), orientation = Orientation.Vertical)
    ){




        ProductCard(productName = checkoutViewModel.productName, imageUrl = checkoutViewModel.uploadedImage.toString())

        for(methods in paymentMethods){
            TextAndRadioButton(
                selectedOption = methods,
                onSelect = {
                    selectedOption = methods
                    isSelected = !isSelected
                },
                isSelected = isSelected
            )
        }



    }

}