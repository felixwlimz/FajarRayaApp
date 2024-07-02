package com.fajarraya.app.components.navigation


sealed class Screen(val route : String) {

    data object Home : Screen("Home")

    data object Orders : Screen("Orders"){
        data object OrderPage : Screen("Order")
        data object AddProduct : Screen("Add Product")
        data object EditProduct : Screen("Edit Product")
        data object ProductList : Screen("Products")
        data object CheckoutPage : Screen("Checkout")
        data object PrintReceiptPage : Screen("Print Receipt")
    }
    data object Transactions : Screen("Transactions")


    data object Profile : Screen("Profile"){
        data object Menu : Screen("Menu")
        data object ProfileDetails : Screen("Details")
        data object Account : Screen("Account")
        data object Language : Screen("Language")
    }
    data object Supplier : Screen("Supplier"){

        data object SupplierList : Screen("Suppliers")
        data object AddEdit : Screen("Add or Edit Supplier")
    }

    data object Login:Screen("Login")
    data object Register:Screen("Register")

    data object Splash:Screen("Splash")

}




