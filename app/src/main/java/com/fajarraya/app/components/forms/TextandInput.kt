package com.fajarraya.app.components.forms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fajarraya.app.constants.WidgetConstants

@Composable
fun TextandInput(
    modifier : Modifier = Modifier,
    textInputTitle : String,
    fieldValue : String,
    onValueChange : (String) -> Unit = {},
    placeholderText: String,
    isError : Boolean = false
){

    Column(modifier = modifier.height(80.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {

        Text(
            text = textInputTitle,
            fontWeight = FontWeight(WidgetConstants.FONT_WEIGHT_BOLD),
            fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp
        )
        
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = fieldValue,
            shape = RoundedCornerShape(20.dp),
            onValueChange = onValueChange,
            isError = isError,
            placeholder = {
                Text(text = placeholderText, fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp)
            },
        )

    }

}