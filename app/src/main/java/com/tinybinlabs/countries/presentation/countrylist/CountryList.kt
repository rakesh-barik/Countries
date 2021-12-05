package com.tinybinlabs.countries.presentation.countrylist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tinybinlabs.countries.domain.Country
import com.tinybinlabs.countries.presentation.Screen
import com.tinybinlabs.countries.presentation.components.ConnectivityMessageView
import com.tinybinlabs.countries.presentation.components.LoadingIndicator

@Composable
fun CountryList(
    loading: Boolean,
    countries: List<Country>,
    messagePair: Pair<Boolean, String>,
    onNavigateToDetailScreen: (String) -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize()) {
            LoadingIndicator(isDisplayed = loading)
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(countries) { country: Country ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                val route =
                                    Screen.DetailScreen.route + "?countryId=${country.id}"
                                onNavigateToDetailScreen(route)
                            }
                    ) {
                        Text(
                            text = country.name ?: "",
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 8.dp,
                                bottom = 8.dp
                            )
                        )
                    }
                }
            }
            ConnectivityMessageView(messagePair)
        }
    }
}
