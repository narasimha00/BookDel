package com.bookdel.app.Navigation

import kotlinx.serialization.Serializable

sealed class ModalNavigationScreens {
    @Serializable
    data object MainScreen: ModalNavigationScreens()

    @Serializable
    data object CartScreen: ModalNavigationScreens()

    @Serializable
    data object AddressScreen: ModalNavigationScreens()

    @Serializable
    data object PaymentScreen: ModalNavigationScreens()

    @Serializable
    data object AboutUsScreen: ModalNavigationScreens()
}