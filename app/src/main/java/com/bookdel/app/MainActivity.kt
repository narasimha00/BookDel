package com.bookdel.app

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.CurrencyRupee
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.CurrencyRupee
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.Payment
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.bookdel.app.Authentication.AuthViewModel
import com.bookdel.app.Navigation.NavigationItem
import com.bookdel.app.Navigation.ModalNavigationScreens
import com.bookdel.app.Navigation.SetupRootNavGraph
import com.bookdel.app.ui.theme.BookDelTheme


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
                SetupRootNavGraph(navController = rememberNavController(), authViewModel, items )
            }
        }
    }
}