package com.tinybinlabs.countries.presentation.favorites

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinybinlabs.countries.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel
@Inject
constructor(
    private val repository: CountryRepository,
) : ViewModel() {

    private val _favState = mutableStateOf(FavoritesState())

    val favState: State<FavoritesState> = _favState

    init {
        viewModelScope.launch {
            _favState.value = favState.value.copy(
                loading = true
            )
            loadFavList()
        }
    }

    private suspend fun loadFavList() {
            repository.getFavoriteCountries()
                .onEach {
                    Log.d("FavoritesViewModel", "loadFavList")
                if (it.isNotEmpty()) {
                    _favState.value = favState.value.copy(
                        loading = false,
                        countries = it
                    )
                } else {
                    _favState.value = favState.value.copy(
                        loading = false,
                    )
                }
            }.launchIn(viewModelScope)
    }
}