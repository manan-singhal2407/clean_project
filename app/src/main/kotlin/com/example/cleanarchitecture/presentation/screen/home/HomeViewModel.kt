package com.example.cleanarchitecture.presentation.screen.home

import androidx.lifecycle.ViewModel
import com.example.cleanarchitecture.presentation.base.navigation.Navigator
import com.example.cleanarchitecture.presentation.base.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    fun navigateToVersionScreen() {
        navigator.navigate(Screen.Version)
    }
}