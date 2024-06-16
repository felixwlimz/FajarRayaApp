package com.fajarraya.app.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fajarraya.app.constants.WidgetConstants
import com.fajarraya.app.ui.theme.PrimaryBlue

@Composable
fun OutlinedPrimaryButton(
    modifier : Modifier = Modifier,
    onClick : () -> Unit,
    buttonText : String
){
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(15.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        border = BorderStroke(1.dp, PrimaryBlue)
    ) {
        Text(
            text = buttonText,
            fontWeight = FontWeight(WidgetConstants.FONT_WEIGHT_SEMI),
            fontSize = WidgetConstants.PRIMARY_FONT_SIZE.sp
        )
    }
}