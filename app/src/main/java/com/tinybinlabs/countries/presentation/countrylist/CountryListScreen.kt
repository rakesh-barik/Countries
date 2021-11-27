package com.tinybinlabs.countries.presentation.countrylist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tinybinlabs.countries.domain.Country

@Composable
fun CountryListScreen(
    viewModel: CountryListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(align = Alignment.CenterHorizontally)
                    ) {
                        Text(text = "Countries")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary
            )
        },

        ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.countries) { country: Country ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.Start)
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                ) {
                    Text(
                        text = country.name ?: "",
                        style = MaterialTheme.typography.h6
                    )
                }
            }
        }

    }
}