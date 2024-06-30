package com.fajarraya.app.pages.orders.addproduct

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import java.io.ByteArrayOutputStream

@Composable
fun AddProductPage(
    modifier: Modifier = Modifier,
    addProductViewModel: AddProductViewModel = koinViewModel(),
    navHostController: NavHostController
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        val context = LocalContext.current

        var uploadedImage by remember {
            mutableStateOf<Uri>(Uri.EMPTY)
        }

        val imagePicker =
            rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                val resultCode = result.resultCode
                val data = result.data
                if (resultCode == Activity.RESULT_OK) {
                    val fileUri = data?.data!!
                    uploadedImage = fileUri
                } else if (resultCode == ImagePicker.RESULT_ERROR) {
                    Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
                }
            }

        var productName by remember {
            mutableStateOf("")
        }

        var description by remember {
            mutableStateOf("")
        }
        var price by remember {
            mutableStateOf("")
        }

        var productCategory by remember {
            mutableStateOf("")
        }
        var productDropdownExpanded by remember {
            mutableStateOf(false)
        }
        var supplier by remember {
            mutableStateOf("")
        }
        var stock by remember {
            mutableStateOf("")
        }
        var supplierDropdownExpanded by remember {
            mutableStateOf(false)
        }
        var kodebarang by remember {
            mutableStateOf("")
        }
        var satuan by remember {
            mutableStateOf("")
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
                productName = it
            },
            placeholderText = "Enter Product Name"
        )
        TextandInput(
            textInputTitle = "Product Description",
            fieldValue = description,
            onValueChange = {
                description = it
            },
            placeholderText = "Enter Product Description"
        )

        TextandDropdown(
            textInputTitle = "Product Category",
            dropdownContent = DummyData.categories,
            onExpandedChange = { state ->
                productDropdownExpanded = state
            },
            displaySelectedItem = productCategory,
            isExpanded = productDropdownExpanded,
            onClick = {
                productCategory = it
                productDropdownExpanded = false
            }
        )

        TextandDropdown(
            textInputTitle = "Supplier",
            dropdownContent = DummyData.suppliers,
            onExpandedChange = { state ->
                supplierDropdownExpanded = state
            },
            displaySelectedItem = supplier,
            isExpanded = supplierDropdownExpanded,
            onClick = {
                supplier = it.supplierId
                supplierDropdownExpanded = false
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
                price = it
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
                satuan = it
            },
            placeholderText = "Enter Product Satuan"
        )

        TextandInput(
            textInputTitle = "Kode Barang",
            fieldValue = kodebarang,
            onValueChange = {
                kodebarang = it
            },
            placeholderText = "Enter Product Code"
        )


        TextandInput(
            textInputTitle = "Stock",
            fieldValue = stock,
            onValueChange = {
                stock = it
            },
            placeholderText = "Stock",
            keyboardOptions = KeyboardOptions(
                keyboardType = androidx.compose.ui.text.input.KeyboardType.Number,
                imeAction = ImeAction.Send,
            )
        )

        PrimaryButton(onClick = {
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
            ){
                navHostController.popBackStack();
            }
        }, buttonText = stringResource(id = R.string.save))


    }
}