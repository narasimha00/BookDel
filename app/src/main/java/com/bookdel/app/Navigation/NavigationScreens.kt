package com.bookdel.app.Navigation

import kotlinx.serialization.Serializable

sealed class NavigationScreens {
    @Serializable
    object MainScreen: NavigationScreens()

    @Serializable
    object CartScreen: NavigationScreens()
}