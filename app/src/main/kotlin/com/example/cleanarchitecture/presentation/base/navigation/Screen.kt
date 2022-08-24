package com.example.cleanarchitecture.presentation.base.navigation

sealed class Screen(override val route: String) : NavigationDestination {
    object Login : Screen("login_screen")
    object Home : Screen("home_screen")
    object Version : Screen("version_screen")
}