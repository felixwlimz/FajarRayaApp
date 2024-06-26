package com.fajarraya.app.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.fajarraya.app.constants.WidgetConstants
import com.fajarraya.app.ui.theme.PrimaryBlue
import com.fajarraya.app.utils.Extensions

@Composable
fun CheckoutCard(
    modifier: Modifier = Modifier,
    productName: String,
    imageUrl: String,
    quantity: Int,
    price: Long
){
    Card(modifier = modifier
        .fillMaxWidth()
        .height(100.dp),
        border = BorderStroke(1.dp, PrimaryBlue),
        colors = CardDefaults.cardColors(contentColor = Color.Black, containerColor = Color.White)
    ){
        Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()){

            AsyncImage(
                model = imageUrl, contentDescription = "product-image",
                modifier = Modifier
                    .clip(RoundedCornerShape(70.dp))
                    .height(70.dp)
                    .width(70.dp),
            )

            Column(
                modifier=Modifier.weight(1f)
            ){

                Text(
                    text = productName,
                    fontWeight = FontWeight(WidgetConstants.FONT_WEIGHT_SEMI),
                    fontSize = WidgetConstants.SUBHEADER_FONT_SIZE.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                )

                Text(
                    text = "${quantity} x ${Extensions.toRupiah(price)}",
                    fontWeight = FontWeight(WidgetConstants.FONT_WEIGHT_SEMI),
                    fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                )

            }



        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}