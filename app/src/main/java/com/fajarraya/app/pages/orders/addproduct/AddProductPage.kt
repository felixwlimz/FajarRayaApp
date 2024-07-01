package com.fajarraya.app.pages.orders.addproduct

import android.app.Activity
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.fajarraya.app.components.buttons.PrimaryButton
import com.fajarraya.app.components.forms.TextandDropdown
import com.fajarraya.app.components.forms.TextandImageInput
import com.fajarraya.app.components.forms.TextandInput
import com.fajarraya.app.R
import com.fajarraya.app.core.domain.model.Products
import com.fajarraya.app.utils.DummyData
import com.github.dhaval2404.imagepicker.ImagePicker
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddProductPage(
    modifier: Modifier = Modifier,
    addProductViewModel: AddProductViewModel = koinViewModel(),
    navHostController: NavHostController,
    kodeProduk: String? = null,
) {
    val uploadedImage by remember {
        derivedStateOf { addProductViewModel.uploadedImage }
    }

    val productName by remember {
        derivedStateOf { addProductViewModel.productName }
    }

    val description by remember {
        derivedStateOf { addProductViewModel.description }
    }

    val price by remember {
        derivedStateOf { addProductViewModel.price }
    }

    val productCategory by remember {
        derivedStateOf { addProductViewModel.productCategory }
    }

    val productDropdownExpanded by remember {
        derivedStateOf { addProductViewModel.productDropdownExpanded }
    }

    val supplier by remember {
        derivedStateOf { addProductViewModel.supplier }
    }

    val stock by remember {
        derivedStateOf { addProductViewModel.stock }
    }

    val supplierDropdownExpanded by remember {
        derivedStateOf { addProductViewModel.supplierDropdownExpanded }
    }

    val kodebarang by remember {
        derivedStateOf { addProductViewModel.kodebarang }
    }

    val satuan by remember {
        derivedStateOf { addProductViewModel.satuan }
    }

    LaunchedEffect(key1 = kodeProduk) {
        if (kodeProduk != null) {
            addProductViewModel.loadProductData(kodeProduk)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        val context = LocalContext.current


        val imagePicker =
            rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                val resultCode = result.resultCode
                val data = result.data
                if (resultCode == Activity.RESULT_OK) {
                    val fileUri = data?.data!!
                    addProductViewModel.uploadedImage = fileUri
                } else if (resultCode == ImagePicker.RESULT_ERROR) {
                    Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
                }
            }

        TextandImageInput(
            textInputTitle = "Add Product Photo",
            onClick = {
                ImagePicker.with(context as Activity)
                    .compress(1024)
                    .maxResultSize(1080, 1080)
                    .createIntent { intent ->
                        imagePicker.launch(intent)
                    }
            },
            item = uploadedImage,
        )

        TextandInput(
            textInputTitle = "Product Name",
            fieldValue = productName,
            onValueChange = {
                addProductViewModel.productName = it
            },
            placeholderText = "Enter Product Name"
        )
        TextandInput(
            textInputTitle = "Product Description",
            fieldValue = description,
            onValueChange = {
                addProductViewModel.description = it
            },
            placeholderText = "Enter Product Description"
        )

        TextandDropdown(
            textInputTitle = "Product Category",
            dropdownContent = DummyData.categories,
            onExpandedChange = { state ->
                addProductViewModel.productDropdownExpanded = state
            },
            displaySelectedItem = productCategory,
            isExpanded = productDropdownExpanded,
            onClick = {
                addProductViewModel.productCategory = it
                addProductViewModel.productDropdownExpanded = false
            }
        )

        TextandDropdown(
            textInputTitle = "Supplier",
            dropdownContent = DummyData.suppliers,
            onExpandedChange = { state ->
                addProductViewModel.supplierDropdownExpanded = state
            },
            displaySelectedItem = supplier,
            isExpanded = supplierDropdownExpanded,
            onClick = {
                addProductViewModel.supplier = it.supplierId
                addProductViewModel.supplierDropdownExpanded = false
            }
        )
//            isExpanded = dropdownExpanded,
//            onClick = {
//                dropdownExpanded = true
//
//            }

        TextandInput(
            textInputTitle = "Price",
            fieldValue = price,
            onValueChange = {
                addProductViewModel.price = it
            },
            placeholderText = "Price",
            keyboardOptions = KeyboardOptions(
                keyboardType = androidx.compose.ui.text.input.KeyboardType.Number,
                imeAction = ImeAction.Next,
            )
        )

        TextandInput(
            textInputTitle = "Satuan",
            fieldValue = satuan,
            onValueChange = {
                addProductViewModel.satuan = it
            },
            placeholderText = "Enter Product Satuan"
        )

        TextandInput(
            textInputTitle = "Kode Barang",
            fieldValue = kodebarang,
            onValueChange = {
                if(kodeProduk == null){
                    addProductViewModel.kodebarang = it
                }
            },
            placeholderText = "Enter Product Code"
        )


        TextandInput(
            textInputTitle = "Stock",
            fieldValue = stock,
            onValueChange = {
                addProductViewModel.stock = it
            },
            placeholderText = "Stock",
            keyboardOptions = KeyboardOptions(
                keyboardType = androidx.compose.ui.text.input.KeyboardType.Number,
                imeAction = ImeAction.Send,
            )
        )

        PrimaryButton(onClick = {
            if(kodeProduk != null){
                addProductViewModel.editProducts(
                    Products(
                        kodebarang,
                        productName,
                        stock.toInt(),
                        satuan,
                        productCategory,
                        uploadedImage.toString(),
                        description,
                        supplier,
                        price.toLong()
                    )
                ) {
                    navHostController.popBackStack();
                }
            }else{
                addProductViewModel.insertProducts(
                    Products(
                        kodebarang,
                        productName,
                        stock.toInt(),
                        satuan,
                        productCategory,
                        uploadedImage.toString(),
                        description,
                        supplier,
                        price.toLong()
                    )
                ) {
                    navHostController.popBackStack();
                }
            }

        }, buttonText = stringResource(id = R.string.save))


    }
}