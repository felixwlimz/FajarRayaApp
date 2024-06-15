package com.fajarraya.app.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fajarraya.app.R
import com.fajarraya.app.constants.WidgetConstants

@Composable
fun OrderCard(
    modifier : Modifier = Modifier,
    text : String,
){

    Card(modifier = modifier
        .fillMaxWidth()
        .height(80.dp)){
        Row(horizontalArrangement = Arrangement.spacedBy(15.dp
        ), modifier = Modifier.padding(10.dp)){
            Image(painter = painterResource(id = R.drawable.ic_launcher_background), 
                contentDescription = "product-image" )
            Text(text = text, fontWeight = FontWeight(WidgetConstants.FONT_WEIGHT_SEMI), fontSize = WidgetConstants.SUBHEADER_FONT_SIZE.sp)

        }

    }
}