package com.fajarraya.app.components.forms

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fajarraya.app.constants.WidgetConstants

@Composable
fun TextandImageInput(
    modifier: Modifier = Modifier,
    textInputTitle : String,
    onClick : () -> Unit = {}
)
{
    Column(modifier = modifier
        .height(120.dp)
        .fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {

        Text(
            text = textInputTitle,
            fontWeight = FontWeight(WidgetConstants.FONT_WEIGHT_BOLD),
            fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp
        )

        Box(modifier = Modifier
            .fillMaxHeight()
            .width(100.dp)
            .clickable { onClick() }
            .padding(10.dp)
            .border(border = BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(20.dp))
        ){
            Icon(Icons.Default.Add, contentDescription = "add-image", modifier = Modifier.size(WidgetConstants.MEDIUM_ICON_SIZE.dp).align(Alignment.Center) )

        }

    }
}

