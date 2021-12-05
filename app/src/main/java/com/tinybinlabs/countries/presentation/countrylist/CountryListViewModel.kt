package com.tinybinlabs.countries.presentation.countrylist

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinybinlabs.countries.domain.Country
import com.tinybinlabs.countries.presentation.util.InternetConManager
import com.tinybinlabs.countries.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CountryListViewModel
@Inject
constructor(
    private val repository: CountryRepository,
    private val connectionManager: InternetConManager
) : ViewModel() {

    private val _searchBarState: MutableState<SearchBarState> =
        mutableStateOf(SearchBarState.CLOSED)
    val searchBarState: State<SearchBarState> = _searchBarState

    private val _searchTextState: MutableState<String> =
        mutableStateOf("")
    val searchTextState: State<String> = _searchTextState

    private val _state = mutableStateOf(CountriesState())
    val state: State<CountriesState> = _state

    init {
        Log.d("CountryViewModel", "init called")
        onTriggerEvent(CountryListEvent.RefreshListEvent)
    }

    fun updateSearchWidgetState(value: SearchBarState) {
        _searchBarState.value = value
    }

    fun updateSearchTextState(value: String) {
        _searchTextState.value = value
    }

    fun onTriggerEvent(event: CountryListEvent) {
        viewModelScope.launch {
            when (event) {
                is CountryListEvent.RefreshListEvent -> getCountries()
                is CountryListEvent.SearchEvent -> {
                    searchCountry(event.name)
                }
            }
        }
    }

    private suspend fun getCountries() {
        Log.d("CountryViewModel", "getCountries")
        _state.value = state.value.copy(
            loading = true
        )
        repository.getCountries(connectionManager.isNetAvailable.value)
            .onEach {
                processResponse(it)
            }
            .launchIn(viewModelScope)
    }


    private suspend fun searchCountry(name: String) {
        Log.d("CountryViewModel", "searchCountry")
        _state.value = state.value.copy(
            loading = true
        )
        repository.searchCountry(name, connectionManager.isNetAvailable.value)
            .onEach {
                processResponse(it)
            }
            .launchIn(viewModelScope)
    }

    private fun processResponse(it: List<Country>) {
        if (it.isEmpty()) {
            _state.value = state.value.copy(
                loading = false,
                showError = Pair(true, "Error fetching Details"),
            )
        } else {
            _state.value = state.value.copy(
                loading = false,
                countries = it,
            )
        }
    }

}