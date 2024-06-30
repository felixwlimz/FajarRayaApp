package com.fajarraya.app.components.forms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
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
    isError : Boolean = false,
    errorText : String = "",
    visualTransformation : VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next
    ),
){

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

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
            visualTransformation = visualTransformation,
            maxLines = 1,
            placeholder = {
                Text(text = placeholderText, fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp)
            },
            supportingText = {
                if(!isError){
                    Text(text = errorText, fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp)
                }
            },
            keyboardOptions = keyboardOptions,

        )

    }

}