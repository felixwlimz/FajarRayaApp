package com.fajarraya.app.components.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.fajarraya.app.components.navigation.Screen
import com.fajarraya.app.R
import com.fajarraya.app.ui.theme.PrimaryBlue

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    currentRoute: String,
    showBottomBar: Boolean = true,
    isAdmin: Boolean = false
) {

    AnimatedVisibility(visible = showBottomBar) {
        NavigationBar(modifier = modifier) {

            val navItems: MutableList<BottomBarItem> = mutableListOf(
                BottomBarItem(
                    title = stringResource(id = R.string.orders),
                    icon = Icons.AutoMirrored.Filled.List,
                    screen = Screen.Orders
                ),
                BottomBarItem(
                    title = "History",
                    icon = Icons.Default.History,
                    screen = Screen.History
                ),
                BottomBarItem(
                    title = stringResource(id = R.string.supplier),
                    icon = ImageVector.vectorResource(id = R.drawable.baseline_inbox_24),
                    screen = Screen.Supplier
                ),
                BottomBarItem(
                    title = stringResource(id = R.string.profile),
                    icon = Icons.Default.AccountCircle,
                    screen = Screen.Profile
                )
            )

            if (isAdmin) {
                navItems.add(
                    0, BottomBarItem(
                        title = stringResource(id = R.string.home),
                        icon = Icons.Default.Home,
                        screen = Screen.Home
                    )
                )
            }


            navItems.map { item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = { Text(item.title, fontSize = 10.sp) },
                    selected = currentRoute == item.screen.route,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = PrimaryBlue,
                        selectedTextColor = PrimaryBlue,
                        indicatorColor = Color.White
                    ),
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    })
            }

        }
    }


}