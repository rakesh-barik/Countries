package com.tinybinlabs.countries.presentation.countrylist

import com.tinybinlabs.countries.domain.Country

data class CountriesState(
    val countries: List<Country> = emptyList(),
    val loading: Boolean = false,
    val showError: Pair<Boolean, String> = Pair(false, "")
)
