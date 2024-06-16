package com.fajarraya.app.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fajarraya.app.constants.WidgetConstants

@Composable
fun SupplierCard(
    modifier: Modifier = Modifier,
    supplierName : String,
    supplierAddress : String,
    phone : String,
    city : String,
    province : String
){


    Card(modifier = modifier
        .fillMaxWidth()
        .height(120.dp),
        border = BorderStroke(1.dp, Color.Black),
        colors = CardDefaults.cardColors(contentColor = Color.Black, containerColor = Color.White)

    ) {
        Column(verticalArrangement = Arrangement.spacedBy(5.dp), modifier = Modifier.padding(10.dp)){

            Text(supplierName,
                fontWeight = FontWeight(WidgetConstants.FONT_WEIGHT_SEMI),
                fontSize = WidgetConstants.PRIMARY_FONT_SIZE.sp
            )

            Text(supplierAddress,
                fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp
            )

            Row(modifier = Modifier
                .fillMaxSize()
                .padding(5.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Column(verticalArrangement = Arrangement.spacedBy(5.dp)){
                    Text(text = "Phone Number : ", fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp)
                    Text(phone,
                        fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp
                    )
                }
                Column(verticalArrangement = Arrangement.spacedBy(5.dp)){
                    Text(text = "City : ", fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp)

                    Text(city,
                        fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp
                    )
                }
                Column(verticalArrangement = Arrangement.spacedBy(5.dp)){
                    Text(text = "Province : ", fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp)

                    Text(province,
                        fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp
                    )
                }
            }


        }
    }


}