package com.tinybinlabs.countries.presentation.countrylist

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.tinybinlabs.countries.presentation.components.MainAppBar

@Composable
fun CountryListScreen(
    viewModel: CountryListViewModel = hiltViewModel(),
    isNetworkAvailable: Boolean,
    onNavigateToDetailScreen: (String) -> Unit
) {
    val state = viewModel.state.value
    val searchWidgetState = viewModel.searchBarState.value
    val searchTextState = viewModel.searchTextState.value

    val scaffoldState = rememberScaffoldState()


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MainAppBar(
                searchBarState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = { viewModel.updateSearchTextState(it) },
                onCloseClicked = { viewModel.updateSearchWidgetState(SearchBarState.CLOSED) },
                onSearchClicked = { viewModel.updateSearchWidgetState(SearchBarState.OPEN) },
                onTriggerSearch = { viewModel.onTriggerEvent(CountryListEvent.SearchEvent(it)) },
                onTriggerRefresh = { viewModel.onTriggerEvent(CountryListEvent.RefreshListEvent) }) {
            }
        },

        ) {

        CountryList(
            loading = state.loading,
            countries = state.countries,
            messagePair = Pair(!isNetworkAvailable,"No Connection"),
            onNavigateToDetailScreen = onNavigateToDetailScreen
        )

    }

}