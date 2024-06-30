package com.fajarraya.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import com.fajarraya.app.ui.theme.PrimaryBlue
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

    val woMenu = arrayOf(
        "Login",
        "Register",
        "Splash"
    )

    Scaffold(
        modifier = modifier,
        bottomBar = {
            currentRoute?.let {
                if(!woMenu.contains(it))
                    BottomBar(
                        navController = navController,
                        currentRoute = currentRoute,
                        showBottomBar = showBottomBar(currentRoute)
                    )
            }
        },
        topBar = {
            if (currentRoute != null && !woMenu.contains(currentRoute)) {
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
        floatingActionButton = {
            if(currentRoute == "Suppliers"){
                FloatingActionButton(
                    onClick = { /*TODO*/ },
                    shape = CircleShape,
                    containerColor = PrimaryBlue
                ) {
                    Icon(Icons.Default.Add, contentDescription = "add-supplier")
                }
            }
        }


        ) { paddingValues ->
        MainRoute(navController = navController, modifier = Modifier.padding(paddingValues))
    }
}







