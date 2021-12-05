package com.tinybinlabs.countries.presentation.country

import com.tinybinlabs.countries.domain.Country

data class CountryState(
    val country: Country? = null,
    val loading: Boolean = false,
    val isFav: Boolean = false
)
