package com.example.cleanarchitecture.presentation.base.navigation

import kotlinx.coroutines.flow.MutableStateFlow

class Navigator {
    var destination: MutableStateFlow<NavigationDestination> = MutableStateFlow(Screen.Login)
    var popUpTo: NavigationDestination? = null

    fun navigate(destination: NavigationDestination) {
        this.destination.value = destination
    }

    fun popAndNavigate(popUpTo: NavigationDestination, navigateTo: NavigationDestination) {
        this.destination.value = navigateTo
        this.popUpTo = popUpTo
    }
}