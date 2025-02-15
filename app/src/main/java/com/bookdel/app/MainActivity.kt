package com.bookdel.app

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.bookdel.app.Navigation.NavBarBody
import com.bookdel.app.Navigation.NavBarHeader
import com.bookdel.app.Navigation.NavigationItem
import com.bookdel.app.Navigation.NavigationScreens
import com.bookdel.app.Navigation.SetupNavGraph
import com.bookdel.app.ui.theme.BookDelTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen: SplashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        var isSplashScreenVisible = true
        splashScreen.setKeepOnScreenCondition { isSplashScreenVisible }
        Handler(Looper.getMainLooper()).postDelayed({
            isSplashScreenVisible = false
        }, 1000)

        val items = listOf(
            NavigationItem(title = "Main", route = NavigationScreens.MainScreen, selectedIcon = Icons.Filled.Home, unselectedIcon = Icons.Outlined.Home),
            NavigationItem(title = "Cart", route = NavigationScreens.CartScreen, selectedIcon = Icons.Filled.ShoppingCart, unselectedIcon = Icons.Outlined.ShoppingCart)
        )

        enableEdgeToEdge()
        setContent {
            BookDelTheme {
//                loginScreen()
                val scope = rememberCoroutineScope()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val navcontroller = rememberNavController()
                ModalNavigationDrawer(
                    drawerContent = {
                        ModalDrawerSheet {
                            NavBarHeader()
                            Spacer(modifier = Modifier.height(16.dp))
                            HorizontalDivider(thickness = 2.dp, modifier = Modifier.height(1.dp).padding(horizontal = 20.dp))
                            Spacer(modifier = Modifier.height(16.dp))
                            NavBarBody(items = items) { item ->
                                navcontroller.navigate(item.route)
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                        }
                    },
                    drawerState = drawerState,
                    gesturesEnabled = drawerState.isOpen
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text("Book Del")
                                },
                                navigationIcon = {
                                    IconButton(
                                        onClick = {
                                            scope.launch {
                                                drawerState.open()
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Menu,
                                            contentDescription = "menu icon"
                                        )
                                    }
                                },
                            )
                        }) { innerPadding ->
                        SetupNavGraph(navController = navcontroller, innerPadding = innerPadding)
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun constructUI(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Book Del",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}