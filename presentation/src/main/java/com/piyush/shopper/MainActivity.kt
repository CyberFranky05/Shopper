package com.piyush.shopper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import com.piyush.shopper.ui.feature.HomeScreen
import com.piyush.shopper.ui.theme.ShopperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShopperTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(navController)
                    }
                ) {

                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        NavHost(
                            navController = navController, startDestination = "home"
                        ) {
                            composable("home") {
                                HomeScreen(navController)
                            }

                            composable("cart") {
                                Box(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Text(text = "Cart Screen")
                                }
                            }

                            composable("profile") {
                                Box(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Text(text = "Profile Screen")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun BottomNavigationBar(navController: NavController){
    NavigationBar {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        val item = listOf(
            BottomNavItems.Home,
            BottomNavItems.Cart,
            BottomNavItems.Profile
        )

        item.forEach{ navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route){
                        navController.graph.startDestinationRoute?.let { startRoute ->
                            popUpTo(startRoute){
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = {
                    Text(text = navItem.title)
                },
                icon = {
                    Image(
                        painter = androidx.compose.ui.res.painterResource(id = navItem.icon),
                        contentDescription = navItem.title,
                        colorFilter = ColorFilter.tint(
                            if(currentRoute== navItem.route) MaterialTheme.colorScheme.primary
                            else Color.Gray
                        )
                    )
                },
                colors = NavigationBarItemDefaults.colors().copy(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )
            )
        }

    }
}


sealed class BottomNavItems(var title: String, var route: String, val icon:Int) {
    object Home : BottomNavItems("Home", "home", icon = R.drawable.ic_home)
    object Cart : BottomNavItems("Cart", "cart", icon =  R.drawable.ic_cart)
    object Profile : BottomNavItems("Profile", "profile", icon = R.drawable.ic_profile_bn)
}