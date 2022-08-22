package com.example.cleanarchitecture.presentation.base.navigation

sealed class Screen(override val route: String) : NavigationDestination {
    object Splash : Screen("splash_screen")
    object Home : Screen("home_screen")
}