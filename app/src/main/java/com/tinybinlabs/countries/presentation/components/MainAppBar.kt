package com.tinybinlabs.countries.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.tinybinlabs.countries.presentation.countrylist.SearchBarState

@Composable
fun MainAppBar(
    searchBarState: SearchBarState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: () -> Unit,
    onTriggerSearch: (String) -> Unit,
    onTriggerRefresh: () -> Unit,
    onTriggerFavorite: () -> Unit
) {
    when (searchBarState) {
        SearchBarState.CLOSED -> {
            DefaultAppBar(
                onSearchClicked = onSearchClicked,
                onTriggerRefresh = onTriggerRefresh,
                onFavoriteClicked = onTriggerFavorite
            )
        }
        SearchBarState.OPEN -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onTriggerSearch = onTriggerSearch
            )
        }
    }
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onTriggerSearch: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.primary
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(text = "Search Countries", color = Color.Gray)
            },
            leadingIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search",
                        tint = Color.White
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    if (text.isNotEmpty()) {
                        onTextChange("")
                    } else {
                        onCloseClicked()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "close search",
                        tint = Color.White
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onTriggerSearch(text)
                    focusManager.clearFocus(force = true)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
            )
        )
    }
}

@Composable
fun DefaultAppBar(
    onSearchClicked: () -> Unit,
    onTriggerRefresh: () -> Unit,
    onFavoriteClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "Countries")
        },
        backgroundColor = MaterialTheme.colors.primary,
        actions = {
            IconButton(onClick = { onSearchClicked() }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "reload list"
                )
            }
            IconButton(onClick = { onTriggerRefresh() }) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "reload list"
                )
            }
            IconButton(onClick = { onFavoriteClicked() }) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "shows list of favorites"
                )
            }
        }
    )

}
