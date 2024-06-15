package com.fajarraya.app.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fajarraya.app.pages.home.HomePage
import com.fajarraya.app.pages.orders.OrdersPage
import com.fajarraya.app.pages.orders.addproduct.AddProductPage
import com.fajarraya.app.pages.orders.editproduct.EditProductPage
import com.fajarraya.app.pages.orders.productlist.ProductListPage
import com.fajarraya.app.pages.profile.ProfilePage
import com.fajarraya.app.pages.profile.account.AccountPage
import com.fajarraya.app.pages.profile.detail.ProfileDetailPage
import com.fajarraya.app.pages.supplier.SupplierPage
import com.fajarraya.app.pages.transactions.TransactionsPage

@Composable
fun MainRoute(modifier: Modifier = Modifier, navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier){

        composable(Screen.Home.route){
            HomePage()
        }

        navigation(route = Screen.Orders.OrderPage.route, startDestination = Screen.Orders.route){
            composable(Screen.Orders.route){
                OrdersPage()
            }

            composable(Screen.Orders.AddProduct.route){
                AddProductPage()
            }

            composable(Screen.Orders.EditProduct.route){
                EditProductPage()
            }
            composable(Screen.Orders.ProductList.route){
                ProductListPage()
            }
        }

        composable(Screen.Transactions.route){
            TransactionsPage()
        }


        navigation(
            route = Screen.Profile.Menu.route,
            startDestination = Screen.Profile.route
        ){
            composable(Screen.Profile.route){
                ProfilePage(navHostController = navController)
            }
            composable(Screen.Profile.ProfileDetails.route){
                ProfileDetailPage()
            }
            composable(Screen.Profile.Account.route){
                AccountPage(navHostController = navController)
            }

        }

        composable(Screen.Supplier.route){
            SupplierPage()
        }



    }
}
