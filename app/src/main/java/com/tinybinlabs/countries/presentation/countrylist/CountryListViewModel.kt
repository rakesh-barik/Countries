package com.tinybinlabs.countries.presentation.countrylist

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinybinlabs.countries.domain.Country
import com.tinybinlabs.countries.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class CountryListViewModel
@Inject
constructor(
    private val repository: CountryRepository,
    @Named("access_key") private val token: String
) : ViewModel() {

    private val _state = mutableStateOf(CountriesState())
    val state: State<CountriesState> = _state

    init {
        viewModelScope.launch {
            _state.value = state.value.copy(
                loading = true
            )
            getCountries(token)
        }
    }

    private suspend fun getCountries(token: String) {
        Log.d("Countries", "getCountries called in view model.")
        val result: List<Country> = repository.getCountries(token)
        _state.value = state.value.copy(
            countries = result,
            loading = false
        )
    }

}