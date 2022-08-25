package com.example.cleanarchitecture.presentation.screen.version

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.cleanarchitecture.data.network.model.AppVersion
import com.example.cleanarchitecture.presentation.theme.atom.AppButton
import com.example.cleanarchitecture.presentation.theme.atom.FormInput
import com.example.cleanarchitecture.presentation.theme.height48dp
import com.example.cleanarchitecture.presentation.theme.padding16dp
import com.example.cleanarchitecture.presentation.theme.padding20dp
import com.example.cleanarchitecture.presentation.theme.padding5dp

@Composable
fun VersionScreen(versionViewModel: VersionViewModel) {
    val context = LocalContext.current
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

        Spacer(modifier = Modifier.height(height48dp))

        AppButton(
            label = "Save value",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = padding16dp),
            onClick = {
                versionViewModel.saveAppVersion(context, AppVersion(2, "2.0", 1, ""))
            }
        )
    }
}