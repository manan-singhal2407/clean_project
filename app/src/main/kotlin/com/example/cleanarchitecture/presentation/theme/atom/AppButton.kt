package com.example.cleanarchitecture.presentation.theme.atom

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.cleanarchitecture.presentation.theme.Primary70
import com.example.cleanarchitecture.presentation.theme.Secondary20
import com.example.cleanarchitecture.presentation.theme.Secondary40
import com.example.cleanarchitecture.presentation.theme.height48dp
import com.example.cleanarchitecture.presentation.theme.padding24dp
import com.example.cleanarchitecture.presentation.theme.padding8dp

@Composable
fun AppButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.heightIn(height48dp),
        enabled = enabled,
        contentPadding = PaddingValues(
            horizontal = padding24dp,
            vertical = padding8dp
        ),
        shape = MaterialTheme.shapes.small,
        border = null,
        colors = ButtonDefaults.buttonColors(
            containerColor = Primary70,
            disabledContainerColor = Secondary20,
        )
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall,
            color = if (enabled) Secondary20 else Secondary40,
            fontWeight = FontWeight.Bold
        )
    }
}