package com.example.cleanarchitecture.presentation.theme.atom

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.cleanarchitecture.presentation.theme.padding10dp
import com.example.cleanarchitecture.presentation.theme.padding12dp
import com.example.cleanarchitecture.presentation.theme.padding20dp
import com.example.cleanarchitecture.presentation.theme.padding5dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeCard(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.White,
    textColor: Color = Color.Black
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = padding20dp, vertical = padding5dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        onClick = onClick
    ) {
        Text(
            modifier = Modifier.padding(start = padding10dp, top = padding12dp, bottom = padding12dp),
            text = text,
            color = textColor,
            fontWeight = FontWeight.Bold
        )
    }
}