package com.fajarraya.app.pages.orders.addproduct

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fajarraya.app.components.buttons.PrimaryButton
import com.fajarraya.app.components.forms.TextandDropdown
import com.fajarraya.app.components.forms.TextandImageInput
import com.fajarraya.app.components.forms.TextandInput
import com.fajarraya.app.R
import com.fajarraya.app.utils.DummyData
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddProductPage(modifier : Modifier = Modifier){

    Column(modifier = modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ){

       var productName by remember {
           mutableStateOf("")
       }

        var description by remember {
            mutableStateOf("")
        }
        var price by remember {
            mutableStateOf("")
        }

        var dropdownExpanded by remember {
            mutableStateOf(false)
        }


        TextandImageInput(
            textInputTitle = "Add Product Photo"
        )

        TextandInput(
            textInputTitle = "Product Name" ,
            fieldValue = productName,
            onValueChange = {
                  productName = it
            },
            placeholderText = "Enter Product Name"
        )
        TextandInput(
            modifier = Modifier.height(100.dp),
            textInputTitle = "Product Description" ,
            fieldValue = description,
            onValueChange = {
                   description = it
            },
            placeholderText = "Enter Product Description"
        )

        TextandDropdown(
            textInputTitle = "Product Category",
            dropdownContent = DummyData.categories,
            isExpanded = dropdownExpanded,
            onClick = {
                dropdownExpanded = true

            }
        )

        TextandDropdown(
            textInputTitle = "Supplier",
            dropdownContent = DummyData.suppliers,
        )

        TextandInput(
            textInputTitle = "Price" ,
            fieldValue = price,
            onValueChange = {
                 price = it
            },
            placeholderText = "Price"
        )

        PrimaryButton(onClick = {
        }, buttonText = stringResource(id = R.string.save))




    }
}