package com.tinybinlabs.countries.presentation.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable

@Composable
fun DetailAppBar(
    isFav: Boolean,
    onNavigateUp: () -> Unit,
    onTriggerFavorite: (Boolean) -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "Detail")
        },
        backgroundColor = MaterialTheme.colors.primary,
        navigationIcon = {
            IconButton(onClick = { onNavigateUp() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
            }
        },
        actions = {
            FavToggleButton(
                isFav = isFav,
                onToggle = onTriggerFavorite
            )
        }
    )
}