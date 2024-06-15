package com.fajarraya.app.components.forms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fajarraya.app.constants.WidgetConstants
import com.fajarraya.app.ui.theme.PrimaryBlue

@Composable
fun TextandInput(
    modifier : Modifier = Modifier,
    textInputTitle : String,
    fieldValue : String,
    onValueChange : (TextFieldValue) -> Unit = {},
    placeholderText: String
){

    Column(modifier = modifier.height(80.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {

        Text(
            text = textInputTitle,
            fontWeight = FontWeight(WidgetConstants.FONT_WEIGHT_BOLD),
            fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp
        )
        
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = TextFieldValue(fieldValue),
            shape = RoundedCornerShape(15.dp),
            onValueChange = onValueChange,
            placeholder = {
                Text(text = placeholderText, fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp)
            },
            colors = TextFieldDefaults.colors(focusedContainerColor = PrimaryBlue)
        )

    }

}