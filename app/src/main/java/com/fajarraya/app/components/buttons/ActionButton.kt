package com.fajarraya.app.components.buttons

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun ActionButton(
    onClick : () -> Unit = {},
    icon : ImageVector,
    altType : String
){

    IconButton(onClick = onClick) {
        Icon(icon, contentDescription = altType)
    }

}