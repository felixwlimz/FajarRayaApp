package com.fajarraya.app.components.bottombar

import androidx.compose.ui.graphics.vector.ImageVector
import com.fajarraya.app.components.navigation.Screen

data class BottomBarItem(
    val title : String,
    val icon : ImageVector,
    val screen : Screen
)
