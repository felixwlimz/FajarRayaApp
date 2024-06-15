package com.fajarraya.app.pages.orders.addproduct

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
import com.fajarraya.app.components.buttons.PrimaryButton
import com.fajarraya.app.components.forms.TextandDropdown
import com.fajarraya.app.components.forms.TextandImageInput
import com.fajarraya.app.components.forms.TextandInput
import com.fajarraya.app.R

@Composable
fun AddProductPage(modifier : Modifier = Modifier){
    Column(modifier = modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ){


        TextandImageInput(
            textInputTitle = "Add Product Photo"
        )

        TextandInput(
            textInputTitle = "Product Name" ,
            fieldValue = "",
            placeholderText = "Enter Product Name"
        )
        TextandInput(
            modifier = Modifier.height(100.dp),
            textInputTitle = "Product Description" ,
            fieldValue = "",
            placeholderText = "Enter Product Description"
        )

        TextandDropdown<String>(
            textInputTitle = "Product Category",
            dropdownContent = arrayListOf(),
        )

        TextandDropdown<String>(
            textInputTitle = "Supplier",
            dropdownContent = arrayListOf(),
        )

        TextandInput(
            textInputTitle = "Price" ,
            fieldValue = "",
            placeholderText = "Price"
        )

        PrimaryButton(onClick = {}, buttonText = stringResource(id = R.string.save))




    }
}