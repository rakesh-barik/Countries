package com.tinybinlabs.countries.presentation.countrylist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tinybinlabs.countries.domain.Country
import com.tinybinlabs.countries.presentation.Screen
import com.tinybinlabs.countries.presentation.components.LoadingIndicator

@Composable
fun CountryListScreen(
    navController: NavController,
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
        LoadingIndicator(state.loading)
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.countries) { country: Country ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(Screen.DetailScreen.route + "?countryId=${country.id}")
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
    }
}