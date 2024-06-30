package com.fajarraya.app.pages.orders.editproduct

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.fajarraya.app.R
import com.fajarraya.app.components.buttons.PrimaryButton
import com.fajarraya.app.components.forms.TextandInput

@Composable
fun EditProductPage(modifier: Modifier = Modifier, navHostController: NavHostController){

    Column(modifier = modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ){


//        TextandImageInput(
//            textInputTitle = stringResource(id = R.string.add_product_photo),
//            items =
//        )

        TextandInput(
            textInputTitle = stringResource(id = R.string.product_name) ,
            fieldValue = "",
            placeholderText = stringResource(id = R.string.placeholder_product_name)
        )
        TextandInput(
            modifier = Modifier.height(100.dp),
            textInputTitle = stringResource(id = R.string.product_description),
            fieldValue = "",
            placeholderText = stringResource(id = R.string.placeholder_product_description)
        )

//        TextandDropdown(
//            textInputTitle = stringResource(id = R.string.product_category),
//            dropdownContent = arrayListOf("Choose Product Category"),
//        )
//
//        TextandDropdown(
//            textInputTitle = stringResource(id = R.string.supplier),
//            dropdownContent = arrayListOf("Choose Supplier"),
//        )

        TextandInput(
            textInputTitle = stringResource(id = R.string.price),
            fieldValue = "",
            placeholderText = stringResource(id = R.string.price)
        )

        PrimaryButton(onClick = {}, buttonText = stringResource(id = R.string.save))




    }

}