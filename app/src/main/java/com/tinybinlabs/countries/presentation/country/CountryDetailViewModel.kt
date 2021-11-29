package com.tinybinlabs.countries.presentation.country

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinybinlabs.countries.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryDetailViewModel
@Inject
constructor(
    private val repository: CountryRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CountryState())
    val state: State<CountryState> = _state

    init {
        savedStateHandle.get<Int>("countryId")?.let { id ->
            if (id != -1) {
                viewModelScope.launch {
                    repository.getCountryById(id)?.also {
                        _state.value = state.value.copy(
                            country = it,
                            loading = false
                        )
                    }
                }
            }
        }
    }
}