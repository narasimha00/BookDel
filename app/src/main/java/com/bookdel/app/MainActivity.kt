package com.bookdel.app

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyRupee
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.CurrencyRupee
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.bookdel.app.Authentication.AuthViewModel
import com.bookdel.app.Data.UserDetails
import com.bookdel.app.Navigation.NavigationItem
import com.bookdel.app.Navigation.ModalNavigationScreens
import com.bookdel.app.Navigation.SetupRootNavGraph
import com.bookdel.app.ui.theme.BookDelTheme
import com.bookdel.app.util.DatastoreManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    @SuppressLint("ViewModelConstructorInComposable")
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen: SplashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        var isSplashScreenVisible = true
        splashScreen.setKeepOnScreenCondition { isSplashScreenVisible }
        Handler(Looper.getMainLooper()).postDelayed({
            isSplashScreenVisible = false
        }, 1000)

        val dsManager = DatastoreManager(this)

        val items = listOf(
            NavigationItem(title = "Main", route = ModalNavigationScreens.MainScreen, selectedIcon = Icons.Filled.Home, unselectedIcon = Icons.Outlined.Home),
            NavigationItem(title = "Cart", route = ModalNavigationScreens.CartScreen, selectedIcon = Icons.Filled.ShoppingCart, unselectedIcon = Icons.Outlined.ShoppingCart),
            NavigationItem(title = "Address", route = ModalNavigationScreens.AddressScreen, selectedIcon = Icons.Filled.LocationOn, unselectedIcon = Icons.Outlined.LocationOn),
            NavigationItem(title = "Payment", route = ModalNavigationScreens.PaymentScreen, selectedIcon = Icons.Filled.CurrencyRupee, unselectedIcon = Icons.Outlined.CurrencyRupee),
            NavigationItem(title = "About Us", route = ModalNavigationScreens.AboutUsScreen, selectedIcon = Icons.Filled.Info, unselectedIcon = Icons.Outlined.Info)
        )

        enableEdgeToEdge()
        setContent {
            BookDelTheme {
                val authViewModel = AuthViewModel()
                SetupRootNavGraph(navController = rememberNavController(), authViewModel, items, dsManager )
            }
        }
    }
}