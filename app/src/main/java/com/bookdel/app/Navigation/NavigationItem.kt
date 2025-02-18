package com.bookdel.app.Navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val route: ModalNavigationScreens,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)
