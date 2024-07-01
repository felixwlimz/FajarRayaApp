package com.fajarraya.app.components.forms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fajarraya.app.constants.WidgetConstants

@Composable
fun TextAndRadioButton(
    modifier: Modifier = Modifier,
    selectedOption: String,
    isSelected: Boolean,
    onSelect: () -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp)
        ,
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        RadioButton(selected = isSelected, onClick = onSelect)
        Text(
            selectedOption,
            fontWeight = FontWeight(WidgetConstants.FONT_WEIGHT_SEMI),
            fontSize = WidgetConstants.SUBHEADER_FONT_SIZE.sp
        )
    }

    HorizontalDivider()


}