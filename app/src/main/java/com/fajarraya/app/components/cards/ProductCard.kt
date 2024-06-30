package com.fajarraya.app.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.fajarraya.app.R
import com.fajarraya.app.constants.WidgetConstants
import com.fajarraya.app.ui.theme.PrimaryBlue

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    productName: String,
    onButtonClick: () -> Unit = {},
    imageUrl: String
){
    Card(modifier = modifier
        .fillMaxWidth()
        .height(100.dp),
        border = BorderStroke(1.dp, PrimaryBlue),
        colors = CardDefaults.cardColors(contentColor = Color.Black, containerColor = Color.White)
    ){
        Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp).fillMaxSize()){

            AsyncImage(
                model = imageUrl, contentDescription = "product-image",
                modifier = Modifier
                    .clip(RoundedCornerShape(70.dp))
                    .height(70.dp)
                    .width(70.dp),
            )

            Text(
                text = productName,
                fontWeight = FontWeight(WidgetConstants.FONT_WEIGHT_SEMI),
                fontSize = WidgetConstants.SUBHEADER_FONT_SIZE.sp,
                textAlign = TextAlign.Center
            )

            Row(horizontalArrangement = Arrangement.spacedBy(5.dp), verticalAlignment = Alignment.CenterVertically){
                IconButton(onClick = onButtonClick) {
                    Icon(Icons.Default.Edit, contentDescription = "add-button" )
                }
                IconButton(onClick = onButtonClick) {
                    Icon(Icons.Default.Delete, contentDescription = "add-button" )
                }

            }

        }
    }
}