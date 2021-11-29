package com.tinybinlabs.countries.presentation.countrylist

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.tinybinlabs.countries.CountriesApplication
import com.tinybinlabs.countries.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class CountryListViewModel
@Inject
constructor(
    private val repository: CountryRepository,
    @Named("access_key") private val token: String,
    app: CountriesApplication
) : AndroidViewModel(app) {

    private val _state = mutableStateOf(CountriesState())
    val state: State<CountriesState> = _state

    init {
        viewModelScope.launch {
            if (hasInternetConnectivity()) {
                getCountries(token)
            } else {
                getCountries(null)
                _state.value = state.value.copy(
                    showError = Pair(true, "No Network Connection")
                )
            }


        }
    }

    private suspend fun getCountries(token: String?) {
        Log.d("Countries", "getCountries called in view model.")
        _state.value = state.value.copy(
            loading = true
        )
        repository.getCountries(token)
            .onEach {
                if (it.isEmpty()) {
                    _state.value = state.value.copy(
                        showError = Pair(true, "Error fetching Details"),
                        loading = false
                    )
                } else {
                    _state.value = state.value.copy(
                        countries = it,
                        loading = false
                    )
                }
            }
            .launchIn(viewModelScope)
    }


    private fun hasInternetConnectivity(): Boolean {
        val connectivityManager = getApplication<CountriesApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }


}