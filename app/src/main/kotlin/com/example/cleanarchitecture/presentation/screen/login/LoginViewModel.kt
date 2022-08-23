package com.example.cleanarchitecture.presentation.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.presentation.base.navigation.Navigator
import com.example.cleanarchitecture.presentation.base.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val navigator: Navigator,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    var text by mutableStateOf("Checking user credentials...")

    init {
        checkUserStatus()
    }

    private fun checkUserStatus() {
        if (firebaseAuth.currentUser != null) {
            text = "User is already logged in"
            navigator.popAndNavigate(Screen.Login, Screen.Home)
        } else {
            text = "Logging user"
            loginRepository.login(firebaseAuth).onEach { dataState ->
                dataState.data?.let { message ->
                    text = message
                    navigator.popAndNavigate(Screen.Login, Screen.Home)
                }
                dataState.error?.let { message ->
                    text = message
                }
            }.launchIn(viewModelScope)
        }
    }
}