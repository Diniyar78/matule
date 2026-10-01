package com.sirazovdiniyar.matule.presentation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object MainNavigation : Route

    @Serializable
    data object MainScreen : Route

    @Serializable
    data object AuthScreen : Route

    @Serializable
    data object HomeScreen : Route

    @Serializable
    data object CatalogScreen : Route

    @Serializable
    data object ProjectScreen : Route

    @Serializable
    data object ProfileScreen : Route
}