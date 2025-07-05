package com.bookdel.app.Screens

import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material3.AlertDialog
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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bookdel.app.Authentication.AuthState
import com.bookdel.app.Authentication.AuthViewModel
import com.bookdel.app.Data.UserDetails
import com.bookdel.app.Navigation.NavBarBody
import com.bookdel.app.Navigation.NavBarHeader
import com.bookdel.app.Navigation.NavigationItem
import com.bookdel.app.Navigation.RootNavigation
import com.bookdel.app.Navigation.SetupModalNavGraph
import com.bookdel.app.ui.theme.LogoutColor
import com.bookdel.app.ui.theme.LogoutColorDark
import com.bookdel.app.util.DatastoreManager
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(items: List<NavigationItem>, authViewModel: AuthViewModel, loginNavController: NavHostController, dsManager: DatastoreManager) {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var showConfirmationDialog = remember { mutableStateOf(false)}
    val authState = authViewModel.authState.observeAsState()
    val userDetails = dsManager.getPreferences().collectAsState(initial = UserDetails())

    if(showConfirmationDialog.value) {
        GetConfirmationAndLogout(
            confirmLogout = {
                authViewModel.signOut()
            },
            showConfirmationDialog = showConfirmationDialog
        )
    }

    @Composable
    fun logoutColor() = if(isSystemInDarkTheme()) LogoutColorDark else LogoutColor

    LaunchedEffect(authState.value) {
        when(authState.value) {
            is AuthState.UnAuthenticated -> {
                loginNavController.popBackStack()
                loginNavController.navigate(RootNavigation.Login)
            }

            else -> {}
        }
    }

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column (
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    NavBarHeader(userDetails.value.username)
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(thickness = 2.dp, modifier = Modifier.height(1.dp).padding(horizontal = 20.dp))
                    Spacer(modifier = Modifier.height(16.dp))
                    NavBarBody(items = items) { item ->
                        navController.popBackStack()
                        navController.navigate(item.route)
                        scope.launch {
                            drawerState.close()
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(thickness = 2.dp, modifier = Modifier.height(1.dp).padding(horizontal = 20.dp))
                    Spacer(modifier = Modifier.height(16.dp))
                    NavigationDrawerItem(
                        label = {
                            Text("Log Out", fontSize = 15.sp, textAlign = TextAlign.Center, color = logoutColor())
                        },
                        selected = false,
                        modifier = Modifier
                            .padding(vertical = 15.dp, horizontal = 18.dp)
                            .border(width = 1.5.dp, color = logoutColor(), shape = RoundedCornerShape(size = 50.dp))
                        ,
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.ExitToApp,
                                contentDescription = "logout icon",
                                tint = logoutColor(),
                            )
                        },
                        onClick = {
                           // authViewModel.signOut()
                            showConfirmationDialog.value = true
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


@Composable
fun GetConfirmationAndLogout(confirmLogout: () -> Unit, showConfirmationDialog: MutableState<Boolean>) {
    AlertDialog(
        title = {
            Text("Confirm Logout")
        },
        text = {
            Text("Are you sure you want to log out?\nThis action can't be undone and you have to relogin with your credentials to be able to use this app again.")
        },
        onDismissRequest = {
            showConfirmationDialog.value = false
        },
        confirmButton = {
            TextButton(
                onClick = {
                    confirmLogout()
                }
            ) {
                Text("Log Out")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    showConfirmationDialog.value = false
                }
            ) {
                Text("Cancel")
            }
        },
    )
}