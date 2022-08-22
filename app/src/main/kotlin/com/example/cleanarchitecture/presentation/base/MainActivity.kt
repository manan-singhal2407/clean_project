package com.example.cleanarchitecture.presentation.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cleanarchitecture.presentation.base.navigation.Navigator
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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Text(text = "Hello Manan Singhal")
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