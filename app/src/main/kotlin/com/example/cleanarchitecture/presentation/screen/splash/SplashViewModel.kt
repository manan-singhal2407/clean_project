package com.example.cleanarchitecture.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.presentation.base.navigation.Navigator
import com.example.cleanarchitecture.presentation.base.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val SPLASH_SCREEN_DELAY = 2000L

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    init {
        showHomeScreen()
    }

    private fun showHomeScreen() {
        viewModelScope.launch {
            delay(SPLASH_SCREEN_DELAY)
            navigator.popAndNavigate(Screen.Splash, Screen.Home)
        }
    }
}