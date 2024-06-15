package com.fajarraya.app.components.appbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.fajarraya.app.constants.WidgetConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title : String,
    actionBar : @Composable RowScope.() -> Unit = {},
    navigationIcon : @Composable () -> Unit = {}

){

    TopAppBar(
        title = {
        Text(
            text = title,
            fontSize = WidgetConstants.HEADER_FONT_SIZE.sp,
            fontWeight = FontWeight(WidgetConstants.FONT_WEIGHT_BOLD)
        )

    },
        actions = actionBar,
        navigationIcon = navigationIcon

    )


}