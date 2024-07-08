package com.fajarraya.app.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import com.fajarraya.app.components.buttons.ActionButton
import com.fajarraya.app.components.navigation.Screen

object MainComponent {

    @Composable
    fun showBottomBar(currentRoute : String) : Boolean {
        val showBottomBar by rememberSaveable { mutableStateOf(true) }

        return when(currentRoute){
            "Details" -> {
                !showBottomBar
            }
            "Account" -> {
                !showBottomBar
            }
            "Add Product" -> {
                !showBottomBar
            }
            "${Screen.Orders.EditProduct.route}/{kodeproduk}" -> {
                !showBottomBar
            }
            "Products" -> {
                !showBottomBar
            }

            "Add Supplier" -> {
                !showBottomBar
            }
            "${Screen.Supplier.EditSupplier.route}/{id}" -> {
                !showBottomBar
            }



            else -> {
                showBottomBar

            }
        }

    }

    @Composable
    fun ShowActionIcon(currentRoute: String, navController: NavHostController){
        when(currentRoute){
            "Home" -> {
                    ActionButton(
                        icon = Icons.Default.Notifications,
                        altType = "bell",
                        onClick = {}
                    )
            }
            "Orders" -> {
                ActionButton(
                    icon = Icons.Default.Add,
                    altType = "add",
                    onClick = {
                        navController.navigate(Screen.Orders.AddProduct.route)
                    }
                )
                ActionButton(
                    icon = Icons.Default.List,
                    altType = "list",
                    onClick = {
                        navController.navigate(Screen.Orders.ProductList.route)
                    }
                )


            }
        }
    }

    @Composable
    fun ShowNavIcon(currentRoute: String, navController: NavHostController){
        when(currentRoute){
            "Details" -> {
                ActionButton(
                    icon = Icons.Default.ArrowBack,
                    altType = "arrow-back",
                    onClick = {
                        navController.navigate(Screen.Profile.Menu.route)
                    }
                )
            }
            "Account" -> {
                ActionButton(
                    icon = Icons.Default.ArrowBack,
                    altType = "arrow-back",
                    onClick = {
                        navController.navigate(Screen.Profile.Menu.route)
                    }
                )
            }

            "Add Product" -> {
                ActionButton(
                    icon = Icons.Default.ArrowBack,
                    altType = "arrow-back",
                    onClick = {
                        navController.navigate(Screen.Orders.OrderPage.route)
                    }
                )
            }

            "${Screen.Orders.EditProduct.route}/{kodeproduk}" -> {
                ActionButton(
                    icon = Icons.Default.ArrowBack,
                    altType = "arrow-back",
                    onClick = {
                        navController.navigate(Screen.Orders.OrderPage.route)
                    }
                )

            }

            "Products" -> {
                ActionButton(
                    icon = Icons.Default.ArrowBack,
                    altType = "arrow-back",
                    onClick = {
                        navController.navigate(Screen.Orders.OrderPage.route)
                    }
                )
            }

            "Add Supplier" -> {
                ActionButton(
                    icon = Icons.Default.ArrowBack,
                    altType = "arrow-back",
                    onClick = {
                        navController.navigate(Screen.Supplier.SupplierList.route)
                    }
                )
            }
            "${Screen.Supplier.EditSupplier.route}/{id}" -> {
                ActionButton(
                    icon = Icons.Default.ArrowBack,
                    altType = "arrow-back",
                    onClick = {
                        navController.navigate(Screen.Supplier.SupplierList.route)
                    }
                )
            }
        }
    }


}