package com.tinybinlabs.countries.presentation.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun FavToggleButton(
    isFav: Boolean,
    onToggle: (Boolean) -> Unit
) {
    IconButton(onClick = {
        onToggle(!isFav)
    }) {
        Icon(
            imageVector = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = "add or remove from favorites",
            tint = if (isFav) Color.Red else Color.White
        )
    }

}