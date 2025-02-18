package com.bookdel.app.Navigation

import kotlinx.serialization.Serializable

sealed class ModalNavigationScreens {
    @Serializable
    object MainScreen: ModalNavigationScreens()

    @Serializable
    object CartScreen: ModalNavigationScreens()

    @Serializable
    object AddressScreen: ModalNavigationScreens()

    @Serializable
    object PaymentScreen: ModalNavigationScreens()

    @Serializable
    object AboutUsScreen: ModalNavigationScreens()
}