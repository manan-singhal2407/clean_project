package com.example.cleanarchitecture.presentation.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cleanarchitecture.presentation.base.navigation.Navigator
import com.example.cleanarchitecture.presentation.base.navigation.Screen
import com.example.cleanarchitecture.presentation.screen.home.HomeScreen
import com.example.cleanarchitecture.presentation.screen.login.LoginScreen
import com.example.cleanarchitecture.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val navController = rememberNavController()
                NavigationCallBack(navController)
                NavHost(
                    navController = navController,
                    startDestination = Screen.Login.route
                ) {
                    composable(Screen.Login.route) {
                        LoginScreen(viewModel = hiltViewModel())
                    }
                    composable(Screen.Home.route) {
                        HomeScreen()
                    }
                }
            }
        }
    }

    @Composable
    private fun NavigationCallBack(navController: NavHostController) {
        val destination by navigator.destination.collectAsState()
        LaunchedEffect(destination) {
            if (navController.currentDestination?.route != destination?.route) {
                if (navController.currentDestination?.route == "login_screen") {
                    if (!navController.popBackStack()) {
                        destination?.route?.let {
                            navController.navigate(it) {
                                navigator.popUpTo?.let {
                                    popUpTo(it.route) { inclusive = true }
                                }
                            }
                        }
                    }
                } else {
                    destination?.route?.let {
                        navController.navigate(it) {
                            navigator.popUpTo?.let {
                                popUpTo(it.route) { inclusive = true }
                            }
                        }
                    }
                }
                navigator.popUpTo = null
            }
        }
    }
}