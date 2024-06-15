package com.fajarraya.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fajarraya.app.components.appbar.AppBar
import com.fajarraya.app.components.bottombar.BottomBar
import com.fajarraya.app.components.navigation.MainRoute
import com.fajarraya.app.ui.theme.FajarRayaTheme
import com.fajarraya.app.utils.MainComponent
import com.fajarraya.app.utils.MainComponent.showBottomBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FajarRayaTheme {
                    MainApp()
            }
        }
    }
}

@Composable
fun MainApp(
    modifier : Modifier = Modifier,
    navController : NavHostController = rememberNavController()
) {

    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = currentBackStackEntry?.destination?.route


    Scaffold(
        modifier = modifier,
        bottomBar = {
            currentRoute?.let {
                BottomBar(
                    navController = navController,
                    currentRoute = currentRoute,
                    showBottomBar = showBottomBar(currentRoute)
                )
            }
        },
        topBar = {
            if (currentRoute != null) {
                AppBar(
                    title = currentRoute,
                    actionBar = {
                        MainComponent.ShowActionIcon(
                            currentRoute = currentRoute,
                            navController = navController
                        )
                    },
                    navigationIcon = {
                        MainComponent.ShowNavIcon(
                            currentRoute = currentRoute,
                            navController = navController
                        )
                    }
                )
            }
        },


        ) { paddingValues ->
        MainRoute(navController = navController, modifier = Modifier.padding(paddingValues))
    }
}







