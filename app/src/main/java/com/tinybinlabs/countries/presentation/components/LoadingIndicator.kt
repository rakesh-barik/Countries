package com.tinybinlabs.countries.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoadingIndicator(isDisplayed: Boolean) {
    if (isDisplayed) {
        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
    }
}