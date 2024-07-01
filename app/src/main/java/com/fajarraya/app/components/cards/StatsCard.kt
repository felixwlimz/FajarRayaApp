package com.fajarraya.app.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import com.fajarraya.app.constants.WidgetConstants


@Composable
fun StatsCard(
    modifier : Modifier = Modifier,
    icons : Int,
    totalStats : Long,
    statsText : String,
    cardColor : Color

){

    Card(
        modifier = modifier
            .padding(10.dp)
            .height(152.dp)
            .width(132.dp) ,
        colors = CardDefaults.cardColors(containerColor = cardColor, contentColor = Color.White)
    ) {

        Column(modifier = Modifier.padding(WidgetConstants.CARD_PADDING.dp)){
            Icon(
                modifier = Modifier.size(WidgetConstants.BIG_ICON_SIZE.dp),
                painter = painterResource(icons),
                contentDescription = statsText,
            )

            Text(
                text = totalStats.toString(),
                fontSize = WidgetConstants.HEADER_FONT_SIZE.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = statsText,
                fontSize = WidgetConstants.SUBHEADER_FONT_SIZE.sp,
                fontWeight = FontWeight.SemiBold
            )
        }



    }

}