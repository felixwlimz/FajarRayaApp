package com.fajarraya.app.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.fajarraya.app.pages.auth.login.LoginPage
import com.fajarraya.app.pages.auth.register.RegisterPage
import com.fajarraya.app.pages.home.HomePage
import com.fajarraya.app.pages.orders.OrdersPage
import com.fajarraya.app.pages.orders.addproduct.AddProductPage
import com.fajarraya.app.pages.orders.checkout.CheckoutPage
import com.fajarraya.app.pages.orders.productlist.ProductListPage
import com.fajarraya.app.pages.profile.ProfilePage
import com.fajarraya.app.pages.profile.account.AccountPage
import com.fajarraya.app.pages.profile.detail.ProfileDetailPage
import com.fajarraya.app.pages.splash.SplashPage
import com.fajarraya.app.pages.supplier.SupplierPage
import com.fajarraya.app.pages.supplier.addeditsupplier.AddEditSupplierPage
import com.fajarraya.app.pages.transactions.TransactionsPage

@Composable
fun MainRoute(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = Screen.Splash.route, modifier = modifier
    ) {

        composable(Screen.Home.route) {
            HomePage()
        }

        navigation(route = Screen.Orders.OrderPage.route, startDestination = Screen.Orders.route) {
            composable(Screen.Orders.route) {
                OrdersPage(navHostController = navController)
            }

            composable(Screen.Orders.AddProduct.route) {
                AddProductPage(
                    navHostController = navController,
                    kodeProduk = it.arguments?.getString("kodeproduk")
                )
            }
            composable(
                "${Screen.Orders.EditProduct.route}/{kodeproduk}",
                arguments = listOf(navArgument("kodeproduk") {
                    type = NavType.StringType
                })
            ) {
                AddProductPage(
                    navHostController = navController,
                    kodeProduk = it.arguments?.getString("kodeproduk")
                )
            }
            composable(Screen.Orders.ProductList.route) {
                ProductListPage(navHostController = navController)
            }

            composable(Screen.Orders.CheckoutPage.route) {
                CheckoutPage(navHostController = navController)
            }
        }

        composable(Screen.Transactions.route) {
            TransactionsPage()
        }


        navigation(
            route = Screen.Profile.Menu.route, startDestination = Screen.Profile.route
        ) {
            composable(Screen.Profile.route) {
                ProfilePage(navHostController = navController)
            }
            composable(Screen.Profile.ProfileDetails.route) {
                ProfileDetailPage()
            }
            composable(Screen.Profile.Account.route) {
                AccountPage(navHostController = navController)
            }

        }

        navigation(
            route = Screen.Supplier.route, startDestination = Screen.Supplier.SupplierList.route
        ) {
            composable(Screen.Supplier.SupplierList.route) {
                SupplierPage()
            }
            composable(Screen.Supplier.AddEdit.route) {
                AddEditSupplierPage(
                    navHostController = navController
                )
            }
        }

        composable(Screen.Login.route) {
            LoginPage(navHostController = navController)
        }
        composable(Screen.Register.route) {
            RegisterPage(navHostController = navController)
        }

        composable(Screen.Splash.route) {
            SplashPage(navHostController = navController)
        }


    }
}
