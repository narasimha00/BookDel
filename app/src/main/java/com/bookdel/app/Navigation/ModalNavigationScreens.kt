package com.bookdel.app.Navigation

import kotlinx.serialization.Serializable

sealed class ModalNavigationScreens {
    @Serializable
    object MainScreen: ModalNavigationScreens()

    @Serializable
    object CartScreen: ModalNavigationScreens()
}