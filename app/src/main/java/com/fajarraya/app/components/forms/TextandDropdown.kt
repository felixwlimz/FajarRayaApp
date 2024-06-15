package com.fajarraya.app.components.forms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fajarraya.app.constants.WidgetConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> TextandDropdown(
    modifier : Modifier = Modifier,
    textInputTitle : String,
    isExpanded : Boolean = false,
    onExpandedChange : (Boolean) -> Unit = {},
    dropdownContent : ArrayList<T>,
    onClick : () -> Unit = {}
){

    Column(modifier = modifier.height(80.dp), verticalArrangement = Arrangement.spacedBy(8.dp)){
        Text(
            text = textInputTitle,
            fontWeight = FontWeight(WidgetConstants.FONT_WEIGHT_BOLD),
            fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp
        )

        ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = onExpandedChange, modifier = Modifier.fillMaxWidth()){
            dropdownContent.forEach{ item ->
                DropdownMenuItem(text = {
                    Text(
                        text = item as String,
                        fontWeight = FontWeight(WidgetConstants.FONT_WEIGHT_SEMI),
                        fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp
                    )
                }, onClick = onClick)
            }
        }

    }
}