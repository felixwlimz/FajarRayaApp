package com.fajarraya.app.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fajarraya.app.constants.WidgetConstants

@Composable
fun UserCard(
    modifier : Modifier = Modifier,
    user : String,
    email : String
){

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.height(250.dp)){

        Image(
            imageVector = Icons.Default.Person ,
            contentDescription = null,
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .clip(RoundedCornerShape(50.dp))
        )
        Text(text = user , fontWeight = FontWeight(WidgetConstants.FONT_WEIGHT_BOLD), fontSize = WidgetConstants.HEADER_FONT_SIZE.sp)

        Text(text = email , fontWeight = FontWeight(WidgetConstants.FONT_WEIGHT_SEMI), fontSize = WidgetConstants.PRIMARY_FONT_SIZE.sp)

    }

}