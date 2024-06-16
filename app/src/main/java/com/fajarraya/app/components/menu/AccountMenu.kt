package com.fajarraya.app.components.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fajarraya.app.R
import com.fajarraya.app.constants.WidgetConstants
import com.fajarraya.app.ui.theme.PrimaryBlue

@Composable
fun AccountMenu(
    modifier: Modifier = Modifier,
    onClick : () -> Unit = {},
    text : String,
    isChecked : Boolean = false,
    onCheckedChange : (Boolean) -> Unit = {},
){

    Row(modifier = modifier
        .fillMaxWidth()
        .padding(15.dp)
        .clickable(enabled = text != stringResource(id = R.string.two_step)) {
            onClick()
        },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {


        Text(text = text,
            fontSize = WidgetConstants.PRIMARY_FONT_SIZE.sp,
            fontWeight = FontWeight(WidgetConstants.FONT_WEIGHT_SEMI)
        )

        if(text == stringResource(id = R.string.two_step)){
            Switch(checked = isChecked, onCheckedChange = onCheckedChange, modifier = Modifier.scale(.8f), colors = SwitchDefaults.colors(
                PrimaryBlue
            ))

        } else {
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = "keyboard-arrow")
        }



    }

}
