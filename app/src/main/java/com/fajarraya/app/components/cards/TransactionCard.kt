package com.fajarraya.app.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fajarraya.app.R
import com.fajarraya.app.components.buttons.PrimaryButton
import com.fajarraya.app.constants.WidgetConstants
import com.fajarraya.app.ui.theme.PrimaryBlue

@Composable
fun TransactionCard(
    modifier : Modifier = Modifier,
    title : String,
    quantityDesc : String,
    isCompleted : Boolean
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
        border = BorderStroke(1.dp, PrimaryBlue),
        colors = CardDefaults.cardColors(contentColor = Color.Black, containerColor = Color.White)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "product-image",
                modifier = Modifier
                    .clip(RoundedCornerShape(70.dp))
                    .height(70.dp)
                    .width(70.dp),
            )

            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(
                    text = title,
                    fontWeight = FontWeight(WidgetConstants.FONT_WEIGHT_SEMI),
                    fontSize = WidgetConstants.SUBHEADER_FONT_SIZE.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = quantityDesc,
                    fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp,
                    textAlign = TextAlign.Center
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(5.dp), horizontalAlignment = Alignment.CenterHorizontally ) {
                Text(
                    text = if(isCompleted) stringResource(id = R.string.completed) else stringResource(
                        id = R.string.in_progress
                    ),
                    color = if (isCompleted) Color.Green else Color.Yellow,
                    fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp,
                    textAlign = TextAlign.Center
                )

                PrimaryButton(
                    onClick = {},
                    buttonText = stringResource(id = R.string.details),
                    modifier = Modifier.width(120.dp).height(50.dp)
                )

            }

        }
    }
}