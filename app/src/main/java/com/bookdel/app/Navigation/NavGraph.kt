package com.bookdel.app.Navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bookdel.app.Screens.AboutUsScreen
import com.bookdel.app.Screens.AddressScreen
import com.bookdel.app.Authentication.AuthViewModel
import com.bookdel.app.Screens.CartScreen
import com.bookdel.app.Screens.HomeScreen
import com.bookdel.app.Screens.LoginScreen
import com.bookdel.app.Screens.MainScreen
import com.bookdel.app.Screens.PaymentScreen
import kotlinx.serialization.Serializable

@Composable
fun SetupModalNavGraph(
    innerPadding: PaddingValues,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = ModalNavigationScreens.MainScreen) {
        composable<ModalNavigationScreens.MainScreen> {
            MainScreen(innerPadding = innerPadding)
        }

        composable<ModalNavigationScreens.CartScreen> {
            CartScreen(innerPadding = innerPadding)
        }

        composable<ModalNavigationScreens.AddressScreen> {
            AddressScreen(innerPadding = innerPadding)
        }

        composable<ModalNavigationScreens.PaymentScreen> {
            PaymentScreen(innerPadding = innerPadding)

        }
        composable<ModalNavigationScreens.AboutUsScreen> {
            AboutUsScreen(innerPadding = innerPadding)

        }

    }
}

@Composable
fun SetupRootNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    items: List<NavigationItem>
) {
    NavHost(
        navController = navController,
        startDestination = RootNavigation.Login,
    ) {
        composable<RootNavigation.Login> {
            LoginScreen(navController = navController, authViewModel = authViewModel)
        }
        composable<RootNavigation.Home> {
            HomeScreen(items, authViewModel, navController)
        }
    }
}

sealed class RootNavigation {
    @Serializable
    object Home: RootNavigation()
    @Serializable
    object Login: RootNavigation()
}
