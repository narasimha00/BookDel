package com.bookdel.app.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bookdel.app.Authentication.AuthState
import com.bookdel.app.Authentication.AuthViewModel
import com.bookdel.app.Navigation.NavBarBody
import com.bookdel.app.Navigation.NavBarHeader
import com.bookdel.app.Navigation.NavigationItem
import com.bookdel.app.Navigation.RootNavigation
import com.bookdel.app.Navigation.SetupModalNavGraph
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(items: List<NavigationItem>, authViewModel: AuthViewModel, loginNavController: NavHostController) {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when(authState.value) {
            is AuthState.UnAuthenticated -> {
                loginNavController.popBackStack()
                loginNavController.navigate(RootNavigation.Login)
            }

            else -> Unit
        }
    }

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column (
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    NavBarHeader()
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(thickness = 2.dp, modifier = Modifier.height(1.dp).padding(horizontal = 20.dp))
                    Spacer(modifier = Modifier.height(16.dp))
                    NavBarBody(items = items) { item ->
                        navController.navigate(item.route)
                        scope.launch {
                            drawerState.close()
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    NavigationDrawerItem(
                        label = {
                            Text("Log Out")
                        },
                        selected = false,
                        modifier = Modifier.padding(vertical = 50.dp),
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "logout icon"
                            )
                        },
                        onClick = {
                            authViewModel.signOut()
                        },
                    )
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
            SetupModalNavGraph(navController = navController, innerPadding = innerPadding)
        }
    }
}