package com.tinybinlabs.countries.presentation.favorites

import com.tinybinlabs.countries.domain.Country

data class FavoritesState (
    val countries: List<Country> = emptyList(),
    val loading: Boolean = false
)
