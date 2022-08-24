package com.example.cleanarchitecture.presentation.screen.version

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cleanarchitecture.presentation.theme.atom.FormInput
import com.example.cleanarchitecture.presentation.theme.height48dp
import com.example.cleanarchitecture.presentation.theme.padding20dp
import com.example.cleanarchitecture.presentation.theme.padding5dp

@Composable
fun VersionScreen(versionViewModel: VersionViewModel) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(height48dp))

        FormInput(
            modifier = Modifier.padding(horizontal = padding20dp, vertical = padding5dp),
            label = versionViewModel.appVersion?.versionName ?: "",
            hint = "Version Name"
        )
        FormInput(
            modifier = Modifier.padding(horizontal = padding20dp, vertical = padding5dp),
            label = versionViewModel.appVersion?.versionCode.toString() ?: "1",
            hint = "Version Code"
        )
        FormInput(
            modifier = Modifier.padding(horizontal = padding20dp, vertical = padding5dp),
            label = versionViewModel.appVersion?.forceUpdateCode.toString() ?: "1",
            hint = "Forced version code"
        )
        FormInput(
            modifier = Modifier.padding(horizontal = padding20dp, vertical = padding5dp),
            label = versionViewModel.appVersion?.versionInfo ?: "",
            hint = "Version Info"
        )
    }
}