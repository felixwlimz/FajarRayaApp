package com.fajarraya.app.components.forms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
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
    dropdownContent : List<T>,
    onClick : () -> Unit = {}
){

    Column(modifier = modifier.height(100.dp), verticalArrangement = Arrangement.spacedBy(8.dp)){
        Text(
            text = textInputTitle,
            fontWeight = FontWeight(WidgetConstants.FONT_WEIGHT_BOLD),
            fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp
        )

        ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = onExpandedChange, modifier = Modifier.fillMaxWidth()){

            OutlinedTextField(
                value = textInputTitle ,
                onValueChange = {},
                shape = RoundedCornerShape(20.dp),
                readOnly = true,
                modifier = Modifier.fillMaxWidth().menuAnchor()
            )

            ExposedDropdownMenu(expanded = isExpanded , onDismissRequest = {   }) {
                dropdownContent.forEach{ item ->
                    DropdownMenuItem(
                        modifier = Modifier.fillMaxWidth().padding(5.dp),
                        text = {
                            Text(
                                text = item.toString() ,
                                fontWeight = FontWeight(WidgetConstants.FONT_WEIGHT_SEMI),
                                fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp
                            )
                        }, onClick = onClick
                    )
                }
            }
        }

    }
}