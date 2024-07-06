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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fajarraya.app.components.appbar.AppBar
import com.fajarraya.app.components.bottombar.BottomBar
import com.fajarraya.app.components.navigation.MainRoute
import com.fajarraya.app.components.navigation.Screen
import com.fajarraya.app.core.repository.datastore.RoleRepository
import com.fajarraya.app.models.UserType
import com.fajarraya.app.ui.theme.FajarRayaTheme
import com.fajarraya.app.ui.theme.PrimaryBlue
import com.fajarraya.app.utils.MainComponent
import com.fajarraya.app.utils.MainComponent.showBottomBar
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.androidx.compose.koinViewModel

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

class MainAppViewModel(
    private val roleRepository: RoleRepository,
) : ViewModel(){

    val isAdmin = mutableStateOf(false)

    init {
        roleRepository.getRole()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(it.name== UserType.ADMIN.name){
                    isAdmin.value = true
                }else{
                    isAdmin.value = false
                }
            },{

            })
    }
}

@Composable
fun MainApp(
    modifier : Modifier = Modifier,
    navController : NavHostController = rememberNavController(),
    mainAppViewModel: MainAppViewModel = koinViewModel()
) {

    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = currentBackStackEntry?.destination?.route

    val woMenu = arrayOf(
        "Login",
        "Register",
        "Splash",
    )

    val woTopBar = arrayOf(
        "Print Receipt/{id}",
        "Edit Supplier/{id}"
    )

    val woBottomBar = arrayOf(
        "Checkout",
        "Print Receipt/{id}"
    )

    Scaffold(
        modifier = modifier,
        bottomBar = {
            currentRoute?.let {
                if(!woMenu.contains(it) && !woBottomBar.contains(it))
                    BottomBar(
                        navController = navController,
                        currentRoute = currentRoute,
                        showBottomBar = showBottomBar(currentRoute),
                        isAdmin = mainAppViewModel.isAdmin.value
                    )
            }
        },
        topBar = {
            if (currentRoute != null && !woMenu.contains(currentRoute) && !woTopBar.contains(currentRoute)) {
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
                    onClick = {
                         navController.navigate(Screen.Supplier.AddSupplier.route)
                    },
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







