package com.bookdel.app.Navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bookdel.app.Screens.AboutUsScreen
import com.bookdel.app.Screens.AddressScreen
import com.bookdel.app.Screens.CartScreen
import com.bookdel.app.Screens.MainScreen
import com.bookdel.app.Screens.PaymentScreen

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

        composable<NavigationScreens.AddressScreen> {
            AddressScreen(innerPadding = innerPadding)
        }

        composable<NavigationScreens.PaymentScreen> {
            PaymentScreen(innerPadding = innerPadding)

        }
        composable<NavigationScreens.AboutUsScreen> {
            AboutUsScreen(innerPadding = innerPadding)

        }

    }
}