package com.fajarraya.app.pages.supplier.addeditsupplier

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
import com.fajarraya.app.R
import com.fajarraya.app.components.buttons.PrimaryButton
import com.fajarraya.app.components.forms.TextandInput
import com.fajarraya.app.core.domain.model.Suppliers
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddEditSupplierPage(modifier : Modifier = Modifier, addEditSupplierViewModel: AddEditSupplierViewModel = koinViewModel()){

    var supplierId by remember {
        mutableStateOf("")
    }

    var supplierName by remember {
        mutableStateOf("")
    }


    var supplierAddress by remember {
        mutableStateOf("")
    }


    var description by remember {
        mutableStateOf("")
    }

    var phoneNumber by remember {
        mutableStateOf("")
    }



    var city by remember {
        mutableStateOf("")
    }


    var supplierProvince by remember {
        mutableStateOf("")
    }





    Column(modifier = modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)) {

        TextandInput(
            modifier = Modifier.height(100.dp),
            textInputTitle = "Supplier ID" ,
            fieldValue = supplierId,
            onValueChange = {
                supplierId = it
            },
            placeholderText = "Enter Supplier Name"
        )


        TextandInput(
            modifier = Modifier.height(100.dp),
            textInputTitle = "Supplier Name" ,
            fieldValue = supplierName,
            onValueChange = {
                supplierName = it
            },
            placeholderText = "Enter Supplier Name"
        )
        TextandInput(
            modifier = Modifier.height(100.dp),
            textInputTitle = "Supplier Address" ,
            fieldValue = supplierAddress,
            onValueChange = {
                supplierAddress = it
            },
            placeholderText = "Enter Product Description"
        )

        TextandInput(
            modifier = Modifier.height(100.dp),
            textInputTitle = "Phone Number" ,
            fieldValue = phoneNumber,
            onValueChange = {
                phoneNumber = it
            },
            placeholderText = "Enter Product Description"
        )

        TextandInput(
            modifier = Modifier.height(250.dp),
            textInputTitle = "Description" ,
            fieldValue = description,
            onValueChange = {
                description = it
            },
            placeholderText = "Enter Supplier Name"
        )
        TextandInput(
            modifier = Modifier.height(100.dp),
            textInputTitle = "City/Regency" ,
            fieldValue = city,
            onValueChange = {
                 city = it
            },
            placeholderText = "City"
        )

        TextandInput(
            modifier = Modifier.height(100.dp),
            textInputTitle = "Province" ,
            fieldValue = supplierProvince,
            onValueChange = {
                supplierProvince = it
            },
            placeholderText = "Province"
        )



        PrimaryButton(onClick = {
            val supplier = Suppliers(
                supplierId = supplierId,
                supplierName = supplierName,
                supplierAddress = supplierAddress,
                province = supplierProvince,
                city = city,
                phoneNumber = phoneNumber,
            )
            addEditSupplierViewModel.addSupplier(supplier)
        }, buttonText = stringResource(id = R.string.save))

    }



}