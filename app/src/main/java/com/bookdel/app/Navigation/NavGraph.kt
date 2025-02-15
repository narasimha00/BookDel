package com.bookdel.app.Navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bookdel.app.Screens.CartScreen
import com.bookdel.app.Screens.MainScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(navController = navController, startDestination = NavigationScreens.MainScreen) {
        composable<NavigationScreens.MainScreen> {
            MainScreen(innerPadding = innerPadding)
        }

        composable<NavigationScreens.CartScreen> {
            CartScreen(innerPadding = innerPadding)
        }
    }
}