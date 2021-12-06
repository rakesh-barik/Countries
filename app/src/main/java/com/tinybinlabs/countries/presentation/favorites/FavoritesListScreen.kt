package com.tinybinlabs.countries.presentation.favorites

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tinybinlabs.countries.presentation.countrylist.CountryList

@Composable
fun FavoritesListScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
    navController: NavController,
    ) {
    val state = viewModel.favState.value
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Favorites")
                },
                backgroundColor = MaterialTheme.colors.primary,
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                    }
                }
            )
        },

        ) {
        CountryList(
            loading = state.loading,
            countries = state.countries,
            messagePair = Pair(false, ""),
            onNavigateToDetailScreen = {}
        )
    }
}