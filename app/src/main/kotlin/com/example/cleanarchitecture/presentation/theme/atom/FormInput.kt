package com.example.cleanarchitecture.presentation.theme.atom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.cleanarchitecture.presentation.theme.AppShapes
import com.example.cleanarchitecture.presentation.theme.Error70
import com.example.cleanarchitecture.presentation.theme.Primary80
import com.example.cleanarchitecture.presentation.theme.Secondary20
import com.example.cleanarchitecture.presentation.theme.padding5dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    label: String,
    hint: String,
    modifier: Modifier = Modifier
) {

    var inputText by rememberSaveable { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            shape = AppShapes.small,
            value = inputText,
            onValueChange = { inputText = it },
            placeholder = {
                Text(
                    text = hint,
                    color = Color.LightGray
                )
            },
            label = { Text(label) },
            textStyle = TextStyle(fontSize = MaterialTheme.typography.bodySmall.fontSize),
            singleLine = true,
            trailingIcon = {
                if (inputText.isNotEmpty()) {
                    IconButton(onClick = { inputText = "" }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear Icon",
                            tint = Primary80
                        )
                    }
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Primary80,
                unfocusedBorderColor = Secondary20,
                errorBorderColor = Error70,
                textColor = Primary80
            ),
        )

        Spacer(modifier = Modifier.padding(padding5dp))
    }
}
