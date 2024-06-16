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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fajarraya.app.components.cards.SupplierCard
import com.fajarraya.app.constants.WidgetConstants
import com.fajarraya.app.utils.DummyData

@Composable
fun SupplierPage(modifier : Modifier = Modifier){

    LazyColumn(modifier = modifier
        .fillMaxSize()
        .padding(10.dp), verticalArrangement = Arrangement.spacedBy(15.dp)){

        item {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = TextFieldValue(""),
                shape = RoundedCornerShape(20.dp),
                onValueChange = {},
                placeholder = {
                    Text(text = "Search For Suppliers", fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp)
                },
            )        }

        items(DummyData.suppliers){
            SupplierCard(
                supplierName = it.supplierName,
                supplierAddress = it.supplierAddress,
                phone = it.phone,
                city = it.city,
                province = it.province
            )

        }


    }


}