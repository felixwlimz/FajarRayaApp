package com.fajarraya.app.pages.supplier.addeditsupplier

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.fajarraya.app.R
import com.fajarraya.app.components.buttons.PrimaryButton
import com.fajarraya.app.components.forms.TextandInput
import com.fajarraya.app.core.domain.model.Suppliers
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddEditSupplierPage(
    modifier: Modifier = Modifier,
    addEditSupplierViewModel: AddEditSupplierViewModel = koinViewModel(),
    navHostController: NavHostController,
    supplierID: String? = null
){
    LaunchedEffect(key1 = supplierID) {
        if(supplierID != null)
            addEditSupplierViewModel.loadSupplierData(supplierID)
    }

    val supplierId by remember {
        derivedStateOf { addEditSupplierViewModel.supplierId}
    }
    val supplierName by remember {
        derivedStateOf { addEditSupplierViewModel.supplierName}
    }
    val supplierAddress by remember {
        derivedStateOf { addEditSupplierViewModel.supplierAddress}
    }
    val description by remember {
        derivedStateOf { addEditSupplierViewModel.description}
    }
    val phoneNumber by remember {
        derivedStateOf { addEditSupplierViewModel.phoneNumber}
    }
    val city by remember {
        derivedStateOf { addEditSupplierViewModel.city}
    }
    val supplierProvince by remember {
        derivedStateOf { addEditSupplierViewModel.supplierProvince}
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
                if(supplierID == null) addEditSupplierViewModel.supplierId = it
            },
            placeholderText = "Enter Supplier ID"
        )


        TextandInput(
            modifier = Modifier.height(100.dp),
            textInputTitle = "Supplier Name" ,
            fieldValue = supplierName,
            onValueChange = {
                addEditSupplierViewModel.supplierName = it
            },
            placeholderText = "Enter Supplier Name"
        )
        TextandInput(
            modifier = Modifier.height(100.dp),
            textInputTitle = "Supplier Address" ,
            fieldValue = supplierAddress,
            onValueChange = {
                addEditSupplierViewModel.supplierAddress = it
            },
            placeholderText = "Enter Address"
        )

        TextandInput(
            modifier = Modifier.height(100.dp),
            textInputTitle = "Phone Number" ,
            fieldValue = phoneNumber,
            onValueChange = {
                addEditSupplierViewModel.phoneNumber = it
            },
            placeholderText = "Enter Phone Number"
        )

        TextandInput(
            modifier = Modifier.height(250.dp),
            textInputTitle = "Description" ,
            fieldValue = description,
            onValueChange = {
                addEditSupplierViewModel.description = it
            },
            placeholderText = "Enter Description"
        )
        TextandInput(
            modifier = Modifier.height(100.dp),
            textInputTitle = "City/Regency" ,
            fieldValue = city,
            onValueChange = {
                addEditSupplierViewModel.city = it
            },
            placeholderText = "Enter City"
        )

        TextandInput(
            modifier = Modifier.height(100.dp),
            textInputTitle = "Province" ,
            fieldValue = supplierProvince,
            onValueChange = {
                addEditSupplierViewModel.supplierProvince = it
            },
            placeholderText = "Enter Province"
        )



        PrimaryButton(onClick = {
            val supplier = Suppliers(
                supplierId = supplierId,
                supplierName = supplierName,
                supplierAddress = supplierAddress,
                province = supplierProvince,
                city = city,
                phoneNumber = phoneNumber,
                description = description,
            )
            if(supplierID == null){
                addEditSupplierViewModel.addSupplier(supplier){
                    navHostController.popBackStack()
                }
            }else{
                addEditSupplierViewModel.updateSupplier(supplier){
                    navHostController.popBackStack()
                }
            }
        }, buttonText = stringResource(id = R.string.save))

    }



}