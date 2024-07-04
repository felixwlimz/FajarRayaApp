package com.fajarraya.app.pages.supplier

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fajarraya.app.components.cards.SupplierCard
import com.fajarraya.app.constants.WidgetConstants
import org.koin.androidx.compose.koinViewModel

@Composable
fun SupplierPage(
    modifier: Modifier = Modifier, supplierViewModel: SupplierViewModel = koinViewModel()
) {
    val suppliers by remember{
        derivedStateOf { supplierViewModel.supplierList  }
    }

    LaunchedEffect(key1 = Unit) {
        supplierViewModel.updateSupplier()
    }
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {

        item {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = TextFieldValue(""),
                shape = RoundedCornerShape(20.dp),
                onValueChange = {},
                placeholder = {
                    Text(
                        text = "Search For Suppliers",
                        fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp
                    )
                },
            )
        }

        items(suppliers) {
            SupplierCard(
                supplierName = it.supplierName,
                supplierAddress = it.supplierAddress,
                phone = it.phoneNumber,
                city = it.city,
                province = it.province,
                onDelete = {
                    supplierViewModel.updateSupplier()
                },
                onUpdate = {
                    supplierViewModel.deleteSupplier(it)
                }
            )

        }


    }


}