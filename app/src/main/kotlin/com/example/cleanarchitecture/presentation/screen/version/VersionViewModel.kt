package com.example.cleanarchitecture.presentation.screen.version

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.data.network.model.AppVersion
import com.example.cleanarchitecture.presentation.base.navigation.Navigator
import com.example.cleanarchitecture.presentation.base.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VersionViewModel @Inject constructor(
    private val versionRepository: VersionRepository,
    private val navigator: Navigator
) : ViewModel() {

    var appVersion by mutableStateOf<AppVersion?>(null)

    init {
        fetchAppVersion()
    }

    private fun fetchAppVersion() {
        versionRepository.retrieveAppVersion().onEach { dataState ->
            dataState.data?.let { appVersion ->
                this.appVersion = appVersion
            }
        }.launchIn(viewModelScope)
    }

    fun saveAppVersion(context: Context, appVersion: AppVersion) {
        versionRepository.saveAppVersion(appVersion).onEach { dataState ->
            dataState.data?.let { appVersion ->
                this.appVersion = appVersion
            }
            dataState.error?.let { message ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }.launchIn(viewModelScope)
    }

    fun onBackPressed() {
        navigator.onBackNavigate(Screen.Home)
    }
}