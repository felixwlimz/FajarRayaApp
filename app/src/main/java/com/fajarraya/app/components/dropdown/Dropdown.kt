package com.fajarraya.app.components.dropdown

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Dropdown(
    modifier : Modifier = Modifier,
    content : @Composable () -> Unit,
) {

    var showDropdownMenu by remember { mutableStateOf(false) }

    IconButton(onClick = { showDropdownMenu = !showDropdownMenu }) {
        Icon(Icons.Default.FilterAlt, contentDescription = "filter-icon")
    }

    DropdownMenu(
        modifier = modifier.padding(10.dp),
        expanded = showDropdownMenu, onDismissRequest = { showDropdownMenu = false}) {
        content()
    }



}